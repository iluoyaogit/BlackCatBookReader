package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BooksByTag;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/30.
 */
public interface SearchByAuthorContract {

    interface View extends BaseContract.BaseView {
        void showSearchResultList(List<BooksByTag.TagBook> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getSearchResultList(String author);
    }

}
