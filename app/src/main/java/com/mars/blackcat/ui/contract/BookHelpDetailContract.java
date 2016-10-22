package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookHelp;
import com.mars.blackcat.bean.CommentList;

/**
 * @author lfh.
 * @date 16/9/3
 */
public interface BookHelpDetailContract {

    interface View extends BaseContract.BaseView {

        void showBookHelpDetail(BookHelp data);

        void showBestComments(CommentList list);

        void showBookHelpComments(CommentList list);

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void getBookHelpDetail(String id);

        void getBestComments(String helpId);

        void getBookHelpComments(String helpId, int start, int limit);

    }

}
