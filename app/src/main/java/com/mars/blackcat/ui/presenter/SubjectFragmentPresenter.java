package com.mars.blackcat.ui.presenter;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.base.RxPresenter;
import com.mars.blackcat.bean.BookLists;
import com.mars.blackcat.ui.contract.SubjectFragmentContract;
import com.mars.blackcat.utils.LogUtils;
import com.mars.blackcat.utils.RxUtil;
import com.mars.blackcat.utils.StringUtils;
import com.mars.blackcat.utils.ToastUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author yuyh.
 * @date 2016/8/31.
 */
public class SubjectFragmentPresenter extends RxPresenter<SubjectFragmentContract.View> implements SubjectFragmentContract.Presenter<SubjectFragmentContract.View> {

    private BookApi bookApi;

    @Inject
    public SubjectFragmentPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getBookLists(String duration, String sort, final int start, int limit, String tag, String gender) {
        String key = StringUtils.creatAcacheKey("book-lists", duration, sort, start + "", limit + "", tag, gender);
        Observable<BookLists> fromNetWork = bookApi.getBookLists(duration, sort, start + "", limit + "", tag, gender)
                .compose(RxUtil.<BookLists>rxCacheListHelper(key));

        //依次检查disk、network
        Subscription rxSubscription = Observable.concat(RxUtil.rxCreateDiskObservable(key, BookLists.class), fromNetWork)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookLists>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getBookLists:" + e.toString());
                        mView.showError();
                    }

                    @Override
                    public void onNext(BookLists tags) {
                        mView.showBookList(tags.bookLists, start == 0 ? true : false);
                        if (tags.bookLists == null || tags.bookLists.size() <= 0) {
                            ToastUtils.showSingleToast("暂无相关书单");
                        }
                    }
                });
        addSubscrebe(rxSubscription);
    }
}
