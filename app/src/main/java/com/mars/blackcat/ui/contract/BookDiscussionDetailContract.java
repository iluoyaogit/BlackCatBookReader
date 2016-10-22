package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.CommentList;
import com.mars.blackcat.bean.Disscussion;

/**
 * @author yuyh.
 * @date 16/9/2.
 */
public interface BookDiscussionDetailContract {

    interface View extends BaseContract.BaseView {

        void showBookDisscussionDetail(Disscussion disscussion);

        void showBestComments(CommentList list);

        void showBookDisscussionComments(CommentList list);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getBookDisscussionDetail(String id);

        void getBestComments(String disscussionId);

        void getBookDisscussionComments(String disscussionId, int start, int limit);

    }

}
