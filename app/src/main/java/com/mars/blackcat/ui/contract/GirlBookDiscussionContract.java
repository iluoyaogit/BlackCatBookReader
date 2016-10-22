package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.DiscussionList;

import java.util.List;

/**
 * @author lfh.
 * @date 16/9/8.
 */
public interface GirlBookDiscussionContract {

    interface View extends BaseContract.BaseView {
        void showGirlBookDisscussionList(List<DiscussionList.PostsBean> list, boolean isRefresh);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getGirlBookDisscussionList(String sort, String distillate, int start, int limit);
    }

}
