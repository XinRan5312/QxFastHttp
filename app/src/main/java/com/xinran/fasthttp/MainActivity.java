package com.xinran.fasthttp;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.xinran.fasthttp.db.Repo;
import com.xinran.fasthttp.db.RepoDbObservable;
import com.xinran.fasthttp.retrofitrx.NetWorkFactory;
import com.xinran.fastokhttp.QxHttpManager;


import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    NetWorkFactory mNetWork;
    RepoDbObservable mRepoDbObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNetWork = NetWorkFactory.NetWorkFactoryHelper.newNettWorkFactory();
        mRepoDbObservable = new RepoDbObservable(QxApplication.context);
        tv = $(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNetWork.getReposResultObserver("", "")//从网络获取数据
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<ArrayList<Repo>>() {
                            @Override
                            public void call(ArrayList<Repo> repos) {
                                mRepoDbObservable.insertRepoListToDB(repos);//从网络获取的数据存放到数据库里，此时在insertRepoListToDB方法里通过发布主题，
                                // 通知注册被观察者数据有更新，onResume中就是注册的Obsevable被观察者
                            }
                        });
            }
        });

    }

    private <T extends View> T $(@IdRes int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRepoDbObservable.createObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<Repo>>() {

                    @Override
                    public void call(ArrayList<Repo> repos) {
                        //正常执行操作UI
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //发生错误
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        //执行完毕 相当于onComplete
                    }
                });
    }

    public void walkWithObservable() {
        Observable.OnSubscribe<List<Integer>> onSubscribe = new Observable.OnSubscribe<List<Integer>>() {//我能给订阅的数据是List<Integer>

            @Override
            public void call(Subscriber<? super List<Integer>> subscriber) {
                subscriber.onNext(getDatas());
                subscriber.onCompleted();
            }
        };
        Subscriber<List<String>> subscriber = new Subscriber<List<String>>() {//订阅需要的是List<String>
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> list) {
                //最终数据处理
            }
        };
        Func1<List<Integer>, List<String>> func1 = new Func1<List<Integer>, List<String>>() {//我负责转换成订阅需要的数据类型啊
            @Override
            public List<String> call(List<Integer> listObservable) {
                return getStringList(listObservable);
            }
        };

        Observable
                .create(onSubscribe)
                .map(func1)
                .subscribe(subscriber);
    }

    private List<String> getStringList(List<Integer> listObservable) {
        return null;
    }

    private List<Integer> getDatas() {
        return null;
    }

    public void test() {
        /**
         * flatmap转换方法的参数Func1 new Func1（“上一步的结果（只是针对上一步Observable中包含的数据类型）”,"要转化为的类型一般必须是Observable<?>"）{
         *
         * }
         * map（new Func1（“上一次类型的结果（只是针对上一步Observable中包含的数据类型）”,"要转化为的类型"）{
         *
         * }）
         */
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        Subscription subscription = Observable
                .zip(mNetWork.getAges("", ""), mNetWork.getNamesResultObserver("", ""),//zip负责并发的执行几个耗时的业务，做完后几个业务得到的数据在作为另外一个方法的参数，做其它逻辑
                        new Func2<List<Integer>, List<String>, Observable<String>>() {//Func2<List<Integer>, List<String>, Observable<String>>()前两个参数是并发返回的
                            // 两个Observable中的泛型类型，第三个参数是Observable.zip()方法返回Observable的泛型类型，本例中Observable.zip()返回的结果是Observable<Observable<String>>>
                            @Override
                            public Observable<String> call(List<Integer> integers, List<String> strings) {
                                return getObsevableString(integers, strings);
                            }
                        })
                .flatMap(new Func1<Observable<String>, Observable<Integer>>() {//为了平铺转化
                    @Override
                    public Observable<Integer> call(Observable<String> stringObservable) {
                        return changObsevable(stringObservable);
                    }
                })
                .map(new Func1<Integer, String>() {//just为了转换而生
                    @Override
                    public String call(Integer integer) {
                        return integer.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        //操作下一步前做些事儿
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .finallyDo(new Action0() {
                    @Override
                    public void call() {
                        //就要订阅了啊
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {
                    @Override
                    public void call() {

                    }
                });


        compositeSubscription.add(subscription);//放在容易里只是为了便于随时取消,一般在onDestroy如下面
        //compositeSubscription.unsubscribe();
    }

    private Observable<Integer> changObsevable(Observable<String> stringObservable) {
        return null;
    }

    private Observable<String> getObsevableString(List<Integer> integers, List<String> strings) {
        String[] str = new String[integers.size()];
        for (int i = 0; i < strings.size(); i++) {
            str[i] = integers.get(i) + strings.get(i);
        }

        return Observable.from(str);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QxHttpManager.cancelTag(this);
    }
}
