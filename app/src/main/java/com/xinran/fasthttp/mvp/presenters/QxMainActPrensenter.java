package com.xinran.fasthttp.mvp.presenters;

import com.xinran.fasthttp.mvp.implview.MainActView;
import com.xinran.fasthttp.mvp.retrofit.QxNetApiService;
import com.xinran.fasthttp.mvp.retrofit.QxNetWorker;
import com.xinran.fasthttp.mvp.subscriber.QxBaseSubscriber;
import com.xinran.fasthttp.mvp.subscriber.QxNetCallBack;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by qixinh on 16/6/15.
 */
public class QxMainActPrensenter extends QxBasePresenterImp<MainActView> {
    private QxNetApiService apiService = QxNetWorker.mRetrofit == null ? QxNetWorker.initRetrofit().create(QxNetApiService.class) : QxNetWorker.mRetrofit.create(QxNetApiService.class);
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();//之所以用它，因为他是线程安全的而且还便于取消

    public QxMainActPrensenter(MainActView view) {
        attachAct(view);
    }

    @Override
   public void bindPresenter() {

    }

    public void loadDataFromUrl(String user, String id) {
        Observable<List<String>> observable = apiService.getNamesResultObserver(user, id);
        QxBaseSubscriber<List<String>> subscriber=new QxBaseSubscriber<>(new QxNetCallBack<List<String>>() {
            @Override
            public void onCallSuccess(List<String> data) {
                act.onGetDataSuccess(data);
            }

            @Override
            public void onCallFail(Throwable throwable, String erroMsg) {
                  act.onGetDataFail(throwable);
            }

            @Override
            public void onCallEnd() {
            }
        });
        addSubscription(observable,subscriber);
    }

    private void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    @Override
   public void unbindPresenter() {
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    private void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
