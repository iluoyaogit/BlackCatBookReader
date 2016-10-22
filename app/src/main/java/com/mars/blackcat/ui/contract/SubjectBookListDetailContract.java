package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookListDetail;

/**
 * @author lfh.
 * @date 2016/8/31.
 */
public interface SubjectBookListDetailContract {

    interface View extends BaseContract.BaseView {
        void showBookListDetail(BookListDetail data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookListDetail(String bookListId);
    }
}
