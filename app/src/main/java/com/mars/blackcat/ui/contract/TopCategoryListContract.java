package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.CategoryList;

/**
 * @author lfh.
 * @date 2016/8/30.
 */
public interface TopCategoryListContract {

    interface View extends BaseContract.BaseView {
        void showCategoryList(CategoryList data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getCategoryList();
    }

}
