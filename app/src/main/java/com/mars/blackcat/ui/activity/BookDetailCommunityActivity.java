package com.mars.blackcat.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;

import com.flyco.tablayout.SlidingTabLayout;
import com.hwangjr.rxbus.RxBus;
import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseActivity;
import com.mars.blackcat.base.Constant;
import com.mars.blackcat.bean.other.SelectionEvent;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.ui.adapter.TabPagerAdapter;
import com.mars.blackcat.ui.fragment.BookDetailDiscussionFragment;
import com.mars.blackcat.ui.fragment.BookDetailReviewFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 书籍详情 社区
 */
public class BookDetailCommunityActivity extends BaseActivity {

    public static final String INTENT_ID = "bookId";
    public static final String INTENT_TITLE = "title";
    public static final String INTENT_INDEX = "index";

    public static void startActivity(Context context, String bookId, String title, int index) {
        context.startActivity(new Intent(context, BookDetailCommunityActivity.class)
                .putExtra(INTENT_ID, bookId)
                .putExtra(INTENT_TITLE, title)
                .putExtra(INTENT_INDEX, index));
    }

    private String bookId;
    private String title;
    private int index;

    @Bind(R.id.tablayoutSubRank)
    SlidingTabLayout mTabLayout;
    @Bind(R.id.viewpagerSubRank)
    ViewPager mViewPager;

    private ArrayList<Fragment> mTabContents=new ArrayList<>();
    private TabPagerAdapter mAdapter;
    private String[] mDatas={"讨论","书评"};

    private AlertDialog dialog;
    private int[] select = new int[]{0, 0};

    @Override
    public int getLayoutId() {
        return R.layout.activity_book_detail_community;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        bookId = getIntent().getStringExtra(INTENT_ID);
        title = getIntent().getStringExtra(INTENT_TITLE);
        index = getIntent().getIntExtra(INTENT_INDEX, 0);
        mCommonToolbar.setTitle(title);
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        mTabContents.add(BookDetailDiscussionFragment.newInstance(bookId));
        mTabContents.add(BookDetailReviewFragment.newInstance(bookId));
        mAdapter=new TabPagerAdapter(getSupportFragmentManager(),mTabContents,mDatas);
    }

    @Override
    public void configViews() {
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setViewPager(mViewPager,mDatas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_community, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sort) {
            showSortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        int checked = select[mViewPager.getCurrentItem()];
        dialog = new AlertDialog.Builder(this)
                .setTitle("排序")
                .setSingleChoiceItems(new String[]{"默认排序", "最新发布", "最多评论"},
                        checked, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (select[mViewPager.getCurrentItem()] != which) {
                                    select[mViewPager.getCurrentItem()] = which;
                                    switch (which) {
                                        case 0:
                                            RxBus.get().post(new SelectionEvent(Constant.SortType.DEFAULT));
                                            break;
                                        case 1:
                                            RxBus.get().post(new SelectionEvent(Constant.SortType.CREATED));
                                            break;
                                        case 2:
                                            RxBus.get().post(new SelectionEvent(Constant.SortType.COMMENT_COUNT));
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                dialog.dismiss();
                            }
                        })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
    }

}
