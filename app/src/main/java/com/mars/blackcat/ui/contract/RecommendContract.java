package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookToc;
import com.mars.blackcat.bean.Recommend;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
public interface RecommendContract {

    interface View extends BaseContract.BaseView {
        void showRecommendList(List<Recommend.RecommendBooks> list);

        void showBookToc(String bookId, List<BookToc.mixToc.Chapters> list);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void getRecommendList();
    }

}
