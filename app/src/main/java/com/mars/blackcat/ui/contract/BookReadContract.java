package com.mars.blackcat.ui.contract;

import com.mars.blackcat.base.BaseContract;
import com.mars.blackcat.bean.BookSource;
import com.mars.blackcat.bean.BookToc;
import com.mars.blackcat.bean.ChapterRead;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/7.
 */
public interface BookReadContract {

    interface View extends BaseContract.BaseView {
        void showBookToc(List<BookToc.mixToc.Chapters> list);

        void showChapterRead(ChapterRead.Chapter data, int chapter);

        void showBookSource(List<BookSource> list);

        void netError();//添加网络处理异常接口
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getBookToc(String bookId, String view);

        void getChapterRead(String url, int chapter);

        void getBookSource(String view, String book);
    }

}
