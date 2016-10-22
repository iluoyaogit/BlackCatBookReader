package com.mars.blackcat.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseCommuniteActivity;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.view.SelectionLayout;

import java.util.List;

import butterknife.Bind;

/**
 * 书评区
 */
public class BookReviewActivity extends BaseCommuniteActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, BookReviewActivity.class));
    }

    @Bind(R.id.slOverall)
    SelectionLayout slOverall;

    @Override
    public int getLayoutId() {
        return R.layout.activity_community_book_review;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("书评区");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {
        super.initDatas();
    }

    @Override
    protected List<List<String>> getTabList() {
        return list2;
    }

    @Override
    public void configViews() {

    }
}
