package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.DiscussionList;

import java.util.List;

/**
 * @author yuyh.
 * @date 16/9/2.
 */
public interface BookDiscussionContract {

    interface View extends BaseContract.BaseView {
        void showBookDisscussionList(List<DiscussionList.PostsBean> list, boolean isRefresh);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBookDisscussionList(String block, String sort, String distillate, int start, int
                limit);
    }

}
