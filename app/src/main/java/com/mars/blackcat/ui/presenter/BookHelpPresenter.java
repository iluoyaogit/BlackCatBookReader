package com.mars.blackcat.ui.presenter;

import com.mars.blackcat.api.BookApi;
import com.mars.blackcat.base.RxPresenter;
import com.mars.blackcat.bean.BookHelpList;
import com.mars.blackcat.ui.contract.BookHelpContract;
import com.mars.blackcat.utils.LogUtils;
import com.mars.blackcat.utils.RxUtil;
import com.mars.blackcat.utils.StringUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * @author lfh.
 * @date 16/9/3.
 */
public class BookHelpPresenter extends RxPresenter<BookHelpContract.View> implements BookHelpContract.Presenter {

    private BookApi bookApi;

    @Inject
    public BookHelpPresenter(BookApi bookApi) {
        this.bookApi = bookApi;
    }

    @Override
    public void getBookHelpList(String sort, String distillate, final int start, int limit) {
        String key = StringUtils.creatAcacheKey("book-help-list", "all", sort, start + "", limit + "", distillate);
        Observable<BookHelpList> fromNetWork = bookApi.getBookHelpList("all", sort, start + "", limit + "", distillate)
                .compose(RxUtil.<BookHelpList>rxCacheListHelper(key));

        //依次检查disk、network
        Subscription rxSubscription = Observable.concat(RxUtil.rxCreateDiskObservable(key, BookHelpList.class), fromNetWork)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BookHelpList>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("getBookHelpList:" + e.toString());
                        mView.showError();
                    }

                    @Override
                    public void onNext(BookHelpList list) {
                        boolean isRefresh = start == 0 ? true : false;
                        mView.showBookHelpList(list.helps, isRefresh);
                    }
                });
        addSubscrebe(rxSubscription);
    }

}
