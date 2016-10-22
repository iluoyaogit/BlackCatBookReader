package com.mars.blackcat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseActivity;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.component.DaggerFindComponent;
import com.mars.blackcat.ui.adapter.TabPagerAdapter;
import com.mars.blackcat.ui.fragment.SubRankFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 16/9/1.
 */
public class SubRankActivity extends BaseActivity {

    public static final String INTENT_WEEK = "_id";
    public static final String INTENT_MONTH = "month";
    public static final String INTENT_ALL = "all";
    public static final String INTENT_TITLE = "title";

    public static void startActivity(Context context, String week, String month, String all, String title) {
        context.startActivity(new Intent(context, SubRankActivity.class)
                .putExtra(INTENT_WEEK, week)
                .putExtra(INTENT_MONTH, month)
                .putExtra(INTENT_ALL, all)
                .putExtra(INTENT_TITLE, title));
    }

    private String week;
    private String month;
    private String all;
    private String title;

    @Bind(R.id.tablayoutSubRank)
    SlidingTabLayout mTabLayout;
    @Bind(R.id.viewpagerSubRank)
    ViewPager mViewPager;

    private ArrayList<Fragment> mTabContents=new ArrayList<>();
    private TabPagerAdapter mAdapter;
    private String[] mDatas={"周榜","月榜","总榜"};


    @Override
    public int getLayoutId() {
        return R.layout.activity_sub_rank;
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
        week = getIntent().getStringExtra(INTENT_WEEK);
        month = getIntent().getStringExtra(INTENT_MONTH);
        all = getIntent().getStringExtra(INTENT_ALL);

        title = getIntent().getStringExtra(INTENT_TITLE).split(" ")[0];
        mCommonToolbar.setTitle(title);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mTabContents.add(SubRankFragment.newInstance(week));
        mTabContents.add(SubRankFragment.newInstance(month));
        mTabContents.add(SubRankFragment.newInstance(all));
        mAdapter=new TabPagerAdapter(getSupportFragmentManager(),mTabContents,mDatas);
    }

    @Override
    public void configViews() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager,mDatas);
    }
}
