package com.mars.blackcat.ui.presenter;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.base.RxPresenter;
import com.mars.blackcat.bean.BooksByTag;
import com.mars.blackcat.ui.contract.SearchByAuthorContract;
import com.mars.blackcat.utils.LogUtils;
import com.mars.blackcat.utils.RxUtil;
import com.mars.blackcat.utils.StringUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author yuyh.
 * @date 2016/9/8.
 */
public class SearchByAuthorPresenter extends RxPresenter<SearchByAuthorContract.View> implements SearchByAuthorContract.Presenter {

    private BookApi bookApi;

    @Inject
    public SearchByAuthorPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getSearchResultList(String author) {
        String key = StringUtils.creatAcacheKey("search-by-author", author);
        Observable<BooksByTag> fromNetWork = bookApi.searchBooksByAuthor(author)
                .compose(RxUtil.<BooksByTag>rxCacheListHelper(key));

        //依次检查disk、network
        Subscription rxSubscription = Observable.concat(RxUtil.rxCreateDiskObservable(key, BooksByTag.class), fromNetWork)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BooksByTag>() {
                    @Override
                    public void onNext(BooksByTag booksByTag) {
                        if (mView != null)
                            mView.showSearchResultList(booksByTag.books);
                    }

                    @Override
                    public void onCompleted() {
                        LogUtils.i("complete");
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getSearchResultList:" + e.toString());
                        if (mView != null)
                            mView.showError();
                    }
                });
        addSubscrebe(rxSubscription);
    }

}
