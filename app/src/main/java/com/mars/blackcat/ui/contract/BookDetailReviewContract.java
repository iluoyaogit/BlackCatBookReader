package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.HotReview;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/9/7.
 */
public interface BookDetailReviewContract {

    interface View extends BaseContract.BaseView {
        void showBookDetailReviewList(List<HotReview.Reviews> list, boolean isRefresh);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookDetailReviewList(String bookId, String sort, int start, int limit);
    }
}
