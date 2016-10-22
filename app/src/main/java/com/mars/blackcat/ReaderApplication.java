package com.mars.blackcat;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.mars.blackcat.base.Constant;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.component.DaggerAppComponent;
import com.mars.blackcat.module.AppModule;
import com.mars.blackcat.module.BookApiModule;
import com.mars.blackcat.utils.AppUtils;
import com.mars.blackcat.utils.LogUtils;
import com.mars.blackcat.utils.SharedPreferencesUtil;

/**
 * @author yuyh.
 * @date 2016/8/3.
 */
public class ReaderApplication extends Application {

    private static ReaderApplication sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        initCompoent();
        AppUtils.init(this);
        initPrefs();
        initNightMode();
        //initHciCloud();
    }

    public static ReaderApplication getsInstance() {
        return sInstance;
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .bookApiModule(new BookApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * 初始化SharedPreference
     */
    protected void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
    }

    protected void initNightMode() {
        boolean isNight = SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false);
        LogUtils.d("isNight=" + isNight);
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
