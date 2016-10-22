package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookHelpList;

import java.util.List;

/**
 * @author lfh.
 * @date 16/9/3.
 */
public interface BookHelpContract {

    interface View extends BaseContract.BaseView {
        void showBookHelpList(List<BookHelpList.HelpsBean> list, boolean isRefresh);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getBookHelpList(String sort, String distillate, int start, int limit);
    }

}
