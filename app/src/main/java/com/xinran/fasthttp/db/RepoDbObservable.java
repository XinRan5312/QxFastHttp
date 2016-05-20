package com.xinran.fasthttp.db;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.concurrent.Callable;


import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by qixinh on 16/4/20.
 */
public class RepoDbObservable {
    private PublishSubject<ArrayList<Repo>> mPublishSubject; // 发表主题
    private RepoDbHelper mRepoDbHelper;

    public RepoDbObservable(Context context) {
        mPublishSubject = PublishSubject.create();
        mRepoDbHelper = new RepoDbHelper(context);
    }

    /**
     * PublishSubject和Obserable及Observer的关系它从下面的继承关系可以看出，PulishSubject不但是Observable的子类也是Obsever的子类
     * <p/>
     * public final class PublishSubject<T> extends Subject<T, T>
     * public class Subject<T, R> extends Observable<R> implements Observer<T>
     *
     * @return
     */
    public Observable<ArrayList<Repo>> createObservable() {
        Observable<ArrayList<Repo>> okObservable = Observable.fromCallable(new Callable<ArrayList<Repo>>() {
            @Override
            public ArrayList<Repo> call() throws Exception {
                return getRepoListFromDB();
            }

        });//创建被观察者对象
        Observable<ArrayList<Repo>> resultObservable = okObservable.concatWith(mPublishSubject);//关联发表主题，以便主题变化后我也能及时收到变化
        return resultObservable;
    }

    public ArrayList<Repo> getRepoListFromDB() {
        mRepoDbHelper.openForRead();
        ArrayList<Repo> repos = new ArrayList<>();
        Cursor c = mRepoDbHelper.getAllRepo();
        if (!c.moveToFirst()) {
            return repos; // 返回空
        }

        do {
            // 添加数据
            repos.add(new Repo(
                    c.getString(RepoDbHelper.REPO_ID_COLUMN_POSITION),
                    c.getString(RepoDbHelper.REPO_NAME_COLUMN_POSITION),
                    c.getString(RepoDbHelper.REPO_DESCRIPTION_COLUMN_POSITION),
                    new Repo.Owner(c.getString(RepoDbHelper.REPO_OWNER_COLUMN_POSITION), "", "", "")));
        } while (c.moveToNext());
        c.close();
        mRepoDbHelper.close();
        return repos;
    }

    // 插入Repo列表
    public void insertRepoListToDB(ArrayList<Repo> repos) {
        mRepoDbHelper.open();
        mRepoDbHelper.removeAllRepo();
        for (Repo repo : repos) {
            mRepoDbHelper.addRepo(
                    repo.getId(),
                    repo.getName(),
                    repo.getDescription(),
                    repo.getOwner().getLogin()
            );
        }
        mRepoDbHelper.close();
        mPublishSubject.onNext(repos); // 此时所有关联到这个发布主题的Obsevable都会收到通知然后跟着变化数据，最后反映到subscrible
    }
}
