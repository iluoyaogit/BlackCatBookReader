package com.mars.blackcat.ui.presenter;

import android.util.Log;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.base.RxPresenter;
import com.mars.blackcat.bean.BookDetail;
import com.mars.blackcat.bean.HotReview;
import com.mars.blackcat.bean.RecommendBookList;
import com.mars.blackcat.ui.contract.BookDetailContract;
import com.mars.blackcat.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class BookDetailPresenter extends RxPresenter<BookDetailContract.View> implements BookDetailContract.Presenter<BookDetailContract.View> {

    private BookApi bookApi;

    private static final String TAG = "BookDetailPresenter";

    @Inject
    public BookDetailPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    public void getBookDetail(String bookId) {
        Subscription rxSubscription = bookApi.getBookDetail(bookId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookDetail>() {
                    @Override
                    public void onNext(BookDetail data) {
                        if (data != null && mView != null) {
                            mView.showBookDetail(data);
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getHotReview(String book) {
        Subscription rxSubscription = bookApi.getHotReview(book).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotReview>() {
                    @Override
                    public void onNext(HotReview data) {
                        List<HotReview.Reviews> list = data.reviews;
                        if (list != null && !list.isEmpty() && mView != null) {
                            mView.showHotReview(list);
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getRecommendBookList(String bookId, String limit) {
        Subscription rxSubscription = bookApi.getRecommendBookList(bookId, limit).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RecommendBookList>() {
                    @Override
                    public void onNext(RecommendBookList data) {
                        LogUtils.i(data.booklists);
                        List<RecommendBookList.RecommendBook> list = data.booklists;
                        if (list != null && !list.isEmpty() && mView != null) {
                            mView.showRecommendBookList(list);
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("+++" + e.toString());
                    }
                });
        addSubscrebe(rxSubscription);
    }

}
