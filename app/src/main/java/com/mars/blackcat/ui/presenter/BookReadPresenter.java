package com.mars.blackcat.ui.presenter;

import android.content.Context;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.base.RxPresenter;
import com.mars.blackcat.bean.BookSource;
import com.mars.blackcat.bean.BookToc;
import com.mars.blackcat.bean.ChapterRead;
import com.mars.blackcat.manager.CacheManager;
import com.mars.blackcat.ui.contract.BookReadContract;
import com.mars.blackcat.utils.LogUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author lfh.
 * @date 2016/8/7.
 */
public class BookReadPresenter extends RxPresenter<BookReadContract.View>
        implements BookReadContract.Presenter<BookReadContract.View> {

    private Context mContext;
    private BookApi bookApi;

    @Inject
    public BookReadPresenter(Context mContext, BookApi bookApi) {
        this.mContext = mContext;
        this.bookApi = bookApi;
    }

    @Override
    public void getBookToc(final String bookId, String viewChapters) {
        List<BookToc.mixToc.Chapters> list = CacheManager.getInstance().getTocList(mContext, bookId);
        if (list != null && !list.isEmpty()) {
            mView.showBookToc(list);
            return;
        }

        Subscription rxSubscription = bookApi.getBookToc(bookId, viewChapters).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookToc>() {
                    @Override
                    public void onNext(BookToc data) {
                        CacheManager.getInstance().saveTocList(mContext, bookId, data);
                        List<BookToc.mixToc.Chapters> list = data.mixToc.chapters;
                        if (list != null && !list.isEmpty() && mView != null) {
                            mView.showBookToc(list);
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError: " + e);
                        mView.netError();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getChapterRead(String url, final int chapter) {
        Subscription rxSubscription = bookApi.getChapterRead(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChapterRead>() {
                    @Override
                    public void onNext(ChapterRead data) {
                        if (data.chapter != null && mView != null) {
                            mView.showChapterRead(data.chapter, chapter);
                        } else {
                            mView.showError();
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError: " + e);
                        mView.showError();
                    }
                });
        addSubscrebe(rxSubscription);
    }

    @Override
    public void getBookSource(String viewSummary, String book) {
        Subscription rxSubscription = bookApi.getBookSource(viewSummary, book).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<BookSource>>() {
                    @Override
                    public void onNext(List<BookSource> data) {
                        if (data != null && mView != null) {
                            mView.showBookSource(data);
                        }
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("onError: " + e);
                    }
                });
        addSubscrebe(rxSubscription);
    }
}