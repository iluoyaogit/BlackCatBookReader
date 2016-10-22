package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookReviewList;

import java.util.List;

/**
 * @author lfh.
 * @date 16/9/3.
 */
public interface BookReviewContract {

    interface View extends BaseContract.BaseView {
        void showBookReviewList(List<BookReviewList.ReviewsBean> list, boolean isRefresh);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBookReviewList(String sort, String type, String distillate, int start, int limit);
    }

}
