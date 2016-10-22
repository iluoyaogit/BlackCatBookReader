package com.mars.blackcat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.flyco.tablayout.SlidingTabLayout;
import com.hwangjr.rxbus.RxBus;
import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseActivity;
import com.mars.blackcat.base.Constant;
import com.mars.blackcat.bean.CategoryListLv2;
import com.mars.blackcat.bean.other.SubEvent;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.component.DaggerFindComponent;
import com.mars.blackcat.ui.adapter.MinorAdapter;
import com.mars.blackcat.ui.adapter.TabPagerAdapter;
import com.mars.blackcat.ui.contract.SubCategoryActivityContract;
import com.mars.blackcat.ui.fragment.SubCategoryFragment;
import com.mars.blackcat.ui.presenter.SubCategoryActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/8/31.
 */
public class SubCategoryListActivity extends BaseActivity implements SubCategoryActivityContract.View {

    public static final String INTENT_CATE_NAME = "name";
    public static final String INTENT_GENDER = "gender";
    private String cate = "";
    private String gender = "";

    private String currentMinor = "";

    @Bind(R.id.tablayoutSub)
    SlidingTabLayout mTabLayout;
    @Bind(R.id.viewpagerSub)
    ViewPager mViewPager;

    @Inject
    SubCategoryActivityPresenter mPresenter;

    private ArrayList<Fragment> mTabContents=new ArrayList<>();
    private TabPagerAdapter mAdapter;
    private String[] mDatas={"新书","热门","口碑","完结"};

    private List<String> mMinors = new ArrayList<>();
    private ListPopupWindow mListPopupWindow;
    private MinorAdapter minorAdapter;
    private String[] types = new String[]{Constant.CateType.NEW, Constant.CateType.HOT, Constant.CateType.REPUTATION, Constant.CateType.OVER};

    private MenuItem menuItem = null;

    public static void startActivity(Context context, String name, @Constant.Gender String gender) {
        Intent intent = new Intent(context, SubCategoryListActivity.class);
        intent.putExtra(INTENT_CATE_NAME, name);
        intent.putExtra(INTENT_GENDER, gender);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sub_category_list;
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
        cate = getIntent().getStringExtra(INTENT_CATE_NAME);
        if (menuItem != null) {
            menuItem.setTitle(cate);
        }
        gender = getIntent().getStringExtra(INTENT_GENDER);
        mCommonToolbar.setTitle(cate);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mPresenter.attachView(this);
        mPresenter.getCategoryListLv2();
        mTabContents.add(SubCategoryFragment.newInstance(cate, "", gender, Constant.CateType.NEW));
        mTabContents.add(SubCategoryFragment.newInstance(cate, "", gender, Constant.CateType.HOT));
        mTabContents.add(SubCategoryFragment.newInstance(cate, "", gender, Constant.CateType.REPUTATION));
        mTabContents.add(SubCategoryFragment.newInstance(cate, "", gender, Constant.CateType.OVER));
        mAdapter=new TabPagerAdapter(getSupportFragmentManager(),mTabContents,mDatas);
    }

    @Override
    public void configViews() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager,mDatas);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RxBus.get().post(new SubEvent(currentMinor, types[position]));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showCategoryList(CategoryListLv2 data) {
        mMinors.clear();
        mMinors.add(cate);
        if (gender.equals(Constant.Gender.MALE)) {
            for (CategoryListLv2.MaleBean bean : data.male) {
                if (cate.equals(bean.major)) {
                    mMinors.addAll(bean.mins);
                    break;
                }
            }
        } else {
            for (CategoryListLv2.MaleBean bean : data.female) {
                if (cate.equals(bean.major)) {
                    mMinors.addAll(bean.mins);
                    break;
                }
            }
        }
        minorAdapter = new MinorAdapter(this, mMinors);
        minorAdapter.setChecked(0);
        currentMinor = "";
       RxBus.get().post(new SubEvent(currentMinor, Constant.CateType.NEW));
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sub_category, menu);
        menuItem = menu.findItem(R.id.menu_major);
        if (!TextUtils.isEmpty(cate)) {
            menuItem.setTitle(cate);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_major) {
            showMinorPopupWindow();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMinorPopupWindow() {
        if (mMinors.size() > 0 && minorAdapter != null) {
            if (mListPopupWindow == null) {
                mListPopupWindow = new ListPopupWindow(this);
                mListPopupWindow.setAdapter(minorAdapter);
                mListPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                mListPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mListPopupWindow.setAnchorView(mCommonToolbar);
                mListPopupWindow.setModal(true);
                mListPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        minorAdapter.setChecked(position);
                        if (position > 0) {
                            currentMinor = mMinors.get(position);
                        } else {
                            currentMinor = "";
                        }
                        int current = mViewPager.getCurrentItem();
                        RxBus.get().post(new SubEvent(currentMinor, types[current]));
                        mListPopupWindow.dismiss();
                        mCommonToolbar.setTitle(mMinors.get(position));
                    }
                });
            }
            mListPopupWindow.show();
        }
    }

}
