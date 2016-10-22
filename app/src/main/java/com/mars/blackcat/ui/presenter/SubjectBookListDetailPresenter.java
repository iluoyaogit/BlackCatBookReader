package com.mars.blackcat.ui.presenter;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.base.RxPresenter;
import com.mars.blackcat.bean.BookListDetail;
import com.mars.blackcat.ui.contract.SubjectBookListDetailContract;
import com.mars.blackcat.utils.LogUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author lfh.
 * @date 2016/8/31.
 */
public class SubjectBookListDetailPresenter extends RxPresenter<SubjectBookListDetailContract.View> implements SubjectBookListDetailContract.Presenter<SubjectBookListDetailContract.View> {

    private BookApi bookApi;

    @Inject
    public SubjectBookListDetailPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getBookListDetail(String bookListId) {
        Subscription rxSubscription = bookApi.getBookListDetail(bookListId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookListDetail>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getBookListDetail:" + e.toString());
                        mView.complete();
                    }

                    @Override
                    public void onNext(BookListDetail data) {
                        mView.showBookListDetail(data);
                    }
                });
        addSubscrebe(rxSubscription);
    }

}
