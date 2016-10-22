package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.SearchDetail;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/30.
 */
public interface SearchContract {

    interface View extends BaseContract.BaseView {
        void showHotWordList(List<String> list);

        void showAutoCompleteList(List<String> list);

        void showSearchResultList(List<SearchDetail.SearchBooks> list);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getHotWordList();

        void getAutoCompleteList(String query);

        void getSearchResultList(String query);
    }

}
