package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.RankingList;

/**
 * @author yuyh.
 * @date 16/9/1.
 */
public interface TopRankContract {

    interface View extends BaseContract.BaseView {
        void showRankList(RankingList rankingList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void getRankList();
    }

}
