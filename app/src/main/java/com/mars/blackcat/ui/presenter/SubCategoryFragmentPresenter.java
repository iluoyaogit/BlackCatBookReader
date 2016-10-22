package com.mars.blackcat.ui.presenter;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.base.RxPresenter;
import com.mars.blackcat.bean.BooksByCats;
import com.mars.blackcat.ui.contract.SubCategoryFragmentContract;
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
 * @date 2016/8/31.
 */
public class SubCategoryFragmentPresenter extends RxPresenter<SubCategoryFragmentContract.View> implements SubCategoryFragmentContract.Presenter<SubCategoryFragmentContract.View> {

    private BookApi bookApi;

    @Inject
    public SubCategoryFragmentPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getCategoryList(String gender, final String major, String minor, String type, final int start, int limit) {
        String key = StringUtils.creatAcacheKey("category-list", gender, type, major, minor, start, limit);
        Observable<BooksByCats> fromNetWork = bookApi.getBooksByCats(gender, type, major, minor, start, limit)
                .compose(RxUtil.<BooksByCats>rxCacheListHelper(key));

        //依次检查disk、network
        Subscription rxSubscription = Observable.concat(RxUtil.rxCreateDiskObservable(key, BooksByCats.class), fromNetWork)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BooksByCats>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getCategoryList:" + e.toString());
                        mView.showError();
                    }

                    @Override
                    public void onNext(BooksByCats booksByCats) {
                        mView.showCategoryList(booksByCats, start == 0 ? true : false);
                    }
                });
        addSubscrebe(rxSubscription);
    }


}
