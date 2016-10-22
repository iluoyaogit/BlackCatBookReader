package com.mars.blackcat.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseActivity;
import com.mars.blackcat.component.AppComponent;

import butterknife.ButterKnife;

/**
 * Created by 92915 on 2016/10/21.
 */

public class AboutActivity extends BaseActivity {
    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, AboutActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void initToolBar() {
        mCommonToolbar.setTitle("关于我们");
        mCommonToolbar.setNavigationIcon(R.mipmap.ab_back);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
