package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.CategoryListLv2;

/**
 * @author lfh.
 * @date 2016/8/30.
 */
public interface SubCategoryActivityContract {

    interface View extends BaseContract.BaseView {
        void showCategoryList(CategoryListLv2 data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getCategoryListLv2();
    }

}
