package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookDetail;
import com.mars.blackcat.bean.HotReview;
import com.mars.blackcat.bean.RecommendBookList;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface BookDetailContract {

    interface View extends BaseContract.BaseView {
        void showBookDetail(BookDetail data);

        void showHotReview(List<HotReview.Reviews> list);

        void showRecommendBookList(List<RecommendBookList.RecommendBook> list);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookDetail(String bookId);

        void getHotReview(String book);

        void getRecommendBookList(String bookId, String limit);
    }

}
