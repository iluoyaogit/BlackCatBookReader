package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookLists;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/8/31.
 */
public interface SubjectFragmentContract {

    interface View extends BaseContract.BaseView {
        void showBookList(List<BookLists.BookListsBean> bookLists, boolean isRefresh);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookLists(String duration, String sort, int start, int limit, String tag, String
                gender);
    }
}
