package com.mars.blackcat.ui.fragment;

import android.os.Bundle;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.thread.EventThread;
import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseRVFragment;
import com.mars.blackcat.base.Constant;
import com.mars.blackcat.bean.DiscussionList;
import com.mars.blackcat.bean.other.SelectionEvent;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.component.DaggerCommunityComponent;
import com.mars.blackcat.ui.activity.BookDiscussionDetailActivity;
import com.mars.blackcat.ui.contract.BookDiscussionContract;
import com.mars.blackcat.ui.easyadapter.BookDiscussionAdapter;
import com.mars.blackcat.ui.presenter.BookDiscussionPresenter;

import java.util.List;

/**
 * 综合讨论区Fragment
 *
 * @author yuyh.
 * @date 16/9/2.
 */
public class BookDiscussionFragment extends BaseRVFragment<BookDiscussionPresenter, DiscussionList.PostsBean> implements BookDiscussionContract.View {

    private static final String BUNDLE_BLOCK = "block";

    public static BookDiscussionFragment newInstance(String block) {
        BookDiscussionFragment fragment = new BookDiscussionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_BLOCK, block);
        fragment.setArguments(bundle);
        return fragment;
    }

    private String block = "ramble";
    private String sort = Constant.SortType.DEFAULT;
    private String distillate = Constant.Distillate.ALL;

    @Override
    public int getLayoutResId() {
        return R.layout.common_easy_recyclerview;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerCommunityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initDatas() {
        block = getArguments().getString(BUNDLE_BLOCK);
        RxBus.get().register(this);
    }

    @Override
    public void configViews() {
        initAdapter(BookDiscussionAdapter.class, true, true);
        onRefresh();
    }

    @Override
    public void showBookDisscussionList(List<DiscussionList.PostsBean> list, boolean isRefresh) {
        if (isRefresh) {
            mAdapter.clear();
            start = 0;
        }
        mAdapter.addAll(list);
        start = start + list.size();
    }

    @Override
    public void showError() {
        loaddingError();
    }

    @Override
    public void complete() {
        mRecyclerView.setRefreshing(false);
    }

    @Subscribe(thread= EventThread.MAIN_THREAD)
    public void initCategoryList(SelectionEvent event) {
        mRecyclerView.setRefreshing(true);
        sort = event.sort;
        distillate = event.distillate;
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        mPresenter.getBookDisscussionList(block, sort, distillate, 0, limit);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getBookDisscussionList(block, sort, distillate, start, limit);
    }

    @Override
    public void onItemClick(int position) {
        DiscussionList.PostsBean data = mAdapter.getItem(position);
        BookDiscussionDetailActivity.startActivity(activity, data._id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.get().unregister(this);
    }

}
