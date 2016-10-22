package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BooksByCats;

/**
 * @author yuyh.
 * @date 16/9/1.
 */
public interface SubRankContract {

    interface View extends BaseContract.BaseView {
        void showCategoryList(BooksByCats data);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void getRankList(String id);
    }
}
