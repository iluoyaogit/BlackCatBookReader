package com.mars.blackcat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseActivity;
import com.mars.blackcat.base.Constant;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.manager.SettingManager;
import com.mars.blackcat.service.DownloadBookService;
import com.mars.blackcat.ui.adapter.TabPagerAdapter;
import com.mars.blackcat.ui.fragment.CommunityFragment;
import com.mars.blackcat.ui.fragment.FindFragment;
import com.mars.blackcat.ui.fragment.RecommendFragment;
import com.mars.blackcat.utils.SharedPreferencesUtil;
import com.mars.blackcat.utils.ToastUtils;
import com.mars.blackcat.view.GenderPopupWindow;

import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * https://github.com/JustWayward/BookReader
 */
public class MainActivity extends BaseActivity {


    @Bind(R.id.tablayoutMain)
    SlidingTabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    private ArrayList<Fragment> mTabContents = new ArrayList<>();
    private TabPagerAdapter mAdapter;
    private String[] mDatas = {"追书", "社区", "发现"};

    // 退出时间
    private long currentBackPressedTime = 0;
    // 退出间隔
    private static final int BACK_PRESSED_INTERVAL = 2000;

    private GenderPopupWindow genderPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, DownloadBookService.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setLogo(R.mipmap.logo);
        setTitle("");
    }

    @Override
    public void initDatas() {
        mTabContents.add(RecommendFragment.getInstance(mDatas[0]));
        mTabContents.add(CommunityFragment.getInstance(mDatas[1]));
        mTabContents.add(FindFragment.getInstance(mDatas[2]));
        mAdapter = new TabPagerAdapter(getSupportFragmentManager(), mTabContents, mDatas);
        System.out.println("tab=" + mTabContents.size());
        System.out.println("data" + mDatas.length);
    }

    @Override
    public void configViews() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager, mDatas);
    }

    public void showChooseSexPopupWindow() {
        if (genderPopupWindow == null) {
            genderPopupWindow = new GenderPopupWindow(MainActivity.this);
        }
        if (!SettingManager.getInstance().isUserChooseSex()
                && !genderPopupWindow.isShowing()) {
            genderPopupWindow.showAtLocation(mCommonToolbar, Gravity.CENTER, 0, 0);
        }
    }

    public void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            case R.id.action_scan_local_book:
                break;
            case R.id.action_wifi_book:
                WifiBookActivity.startActivity(this);
                break;
            case R.id.action_feedback:
                FeedbackActivity.startActivity(this);
                break;
            case R.id.action_night_mode:
                if (SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false)) {
                    SharedPreferencesUtil.getInstance().putBoolean(Constant.ISNIGHT, false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    SharedPreferencesUtil.getInstance().putBoolean(Constant.ISNIGHT, true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
                break;
            case R.id.action_settings:
                SettingActivity.startActivity(this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN
                && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
                currentBackPressedTime = System.currentTimeMillis();
                ToastUtils.showToast(getString(R.string.exit_tips));
                return true;
            } else {
                finish(); // 退出
            }
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    /**
     * 显示item中的图片；
     *
     * @param view
     * @param menu
     * @return
     */
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",
                            Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, DownloadBookService.class));
    }

}