package com.mars.blackcat.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseRVActivity;
import com.mars.blackcat.bean.BooksByTag;
import com.mars.blackcat.bean.SearchDetail;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.component.DaggerBookComponent;
import com.mars.blackcat.ui.contract.SearchByAuthorContract;
import com.mars.blackcat.ui.easyadapter.SearchAdapter;
import com.mars.blackcat.ui.presenter.SearchByAuthorPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author yuyh.
 * @date 2016/9/8.
 */
public class SearchByAuthorActivity extends BaseRVActivity<SearchDetail.SearchBooks> implements SearchByAuthorContract.View {

    public static final String INTENT_AUTHOR = "author";

    public static void startActivity(Context context, String author) {
        context.startActivity(new Intent(context, SearchByAuthorActivity.class)
                .putExtra(INTENT_AUTHOR, author));
    }

    @Inject
    SearchByAuthorPresenter mPresenter;

    private String author = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_by_author;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerBookComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        author = getIntent().getStringExtra(INTENT_AUTHOR);
        mCommonToolbar.setTitle(author);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        initAdapter(SearchAdapter.class, false, false);
    }

    @Override
    public void configViews() {
        mPresenter.attachView(this);
        mPresenter.getSearchResultList(author);
    }

    @Override
    public void onItemClick(int position) {
        SearchDetail.SearchBooks data = mAdapter.getItem(position);
        BookDetailActivity.startActivity(this, data._id);
    }

    @Override
    public void showSearchResultList(List<BooksByTag.TagBook> list) {
        List<SearchDetail.SearchBooks> mList = new ArrayList<>();
        for (BooksByTag.TagBook book : list) {
            mList.add(new SearchDetail.SearchBooks(book._id, book.title, book.author, book.cover, book.retentionRatio, book.latelyFollower));
        }
        mAdapter.clear();
        mAdapter.addAll(mList);
    }

    @Override
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }
}
