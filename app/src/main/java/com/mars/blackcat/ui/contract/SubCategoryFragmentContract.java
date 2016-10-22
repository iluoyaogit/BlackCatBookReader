package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BooksByCats;

/**
 * @author lfh.
 * @date 2016/8/30.
 */
public interface SubCategoryFragmentContract {

    interface View extends BaseContract.BaseView {
        void showCategoryList(BooksByCats data, boolean isRefresh);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getCategoryList(String gender, String major, String minor, String type, int start,
                             int limit);
    }

}
