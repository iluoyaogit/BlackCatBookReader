package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookListTags;

/**
 * @author yuyh.
 * @date 2016/8/31.
 */
public interface SubjectBookListContract {

    interface View extends BaseContract.BaseView {
        void showBookListTags(BookListTags data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookListTags();
    }
}
