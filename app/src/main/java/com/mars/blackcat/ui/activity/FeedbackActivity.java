package com.mars.blackcat.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseActivity;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.view.ProgressWebView;

import butterknife.Bind;

public class FeedbackActivity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, FeedbackActivity.class));
    }

    @Bind(R.id.feedbackView)
    ProgressWebView feedbackView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("反馈建议");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        feedbackView.loadUrl("https://github.com/JustWayward/BookReader/issues/new");
    }
}
