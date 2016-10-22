package com.mars.blackcat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.flyco.tablayout.SlidingTabLayout;
import com.hwangjr.rxbus.RxBus;
import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseActivity;
import com.mars.blackcat.bean.BookListTags;
import com.mars.blackcat.bean.other.TagEvent;
import com.mars.blackcat.common.OnRvItemClickListener;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.component.DaggerFindComponent;
import com.mars.blackcat.ui.adapter.SubjectTagsAdapter;
import com.mars.blackcat.ui.adapter.TabPagerAdapter;
import com.mars.blackcat.ui.contract.SubjectBookListContract;
import com.mars.blackcat.ui.fragment.SubjectFragment;
import com.mars.blackcat.ui.presenter.SubjectBookListPresenter;
import com.mars.blackcat.utils.ToastUtils;
import com.mars.blackcat.view.ReboundScrollView;
import com.mars.blackcat.view.SupportDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;



public class SubjectBookListActivity extends BaseActivity implements SubjectBookListContract.View, OnRvItemClickListener<String> {

    @Bind(R.id.tablayoutSubject)
    SlidingTabLayout mTabLayout;
    @Bind(R.id.viewpagerSubject)
    ViewPager mViewPager;
    @Bind(R.id.rsvTags)
    ReboundScrollView rsvTags;

    @Bind(R.id.rvTags)
    RecyclerView rvTags;
    private SubjectTagsAdapter mTagAdapter;
    private List<BookListTags.DataBean> mTagList = new ArrayList<>();

    private ArrayList<Fragment> mTabContents=new ArrayList<>();
    private TabPagerAdapter mAdapter;
    private String[] mDatas={"本周最热","最新发布","最多收藏"};

    @Inject
    SubjectBookListPresenter mPresenter;

    private String currentTag = "";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SubjectBookListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_subject_book_list_tag;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFindComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle(R.string.subject_book_list);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mTabContents.add(SubjectFragment.newInstance("", 0));
        mTabContents.add(SubjectFragment.newInstance("", 1));
        mTabContents.add(SubjectFragment.newInstance("", 2));
        mAdapter=new TabPagerAdapter(getSupportFragmentManager(),mTabContents,mDatas);
    }

    @Override
    public void configViews() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager,mDatas);

        rvTags.setHasFixedSize(true);
        rvTags.setLayoutManager(new LinearLayoutManager(this));
        rvTags.addItemDecoration(new SupportDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mTagAdapter = new SubjectTagsAdapter(this, mTagList);
        mTagAdapter.setItemClickListener(this);
        rvTags.setAdapter(mTagAdapter);

        mPresenter.attachView(this);
        mPresenter.getBookListTags();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subject, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_tags) {
            if (isVisible(rsvTags)) {
                hideTagGroup();
            } else {
                showTagGroup();
            }
            return true;
        } else if (item.getItemId() == R.id.menu_my_book_list) {
            startActivity(new Intent(this, MyBookListActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (isVisible(rsvTags)) {
            hideTagGroup();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void showBookListTags(BookListTags data) {
        mTagList.clear();
        mTagList.addAll(data.data);
        mTagAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
    }

    @Override
    public void onItemClick(View view, int position, String data) {
        hideTagGroup();
        currentTag = data;
        RxBus.get().post(new TagEvent(currentTag));
    }

    private void showTagGroup() {
        if (mTagList.isEmpty()) {
            ToastUtils.showToast(getString(R.string.network_error_tips));
            return;
        }
        Animation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f,
                Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(400);
        rsvTags.startAnimation(mShowAction);
        rsvTags.setVisibility(View.VISIBLE);
    }

    private void hideTagGroup() {
        Animation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, -1.0f);
        mHiddenAction.setDuration(400);
        rsvTags.startAnimation(mHiddenAction);
        rsvTags.setVisibility(View.GONE);
    }

}
