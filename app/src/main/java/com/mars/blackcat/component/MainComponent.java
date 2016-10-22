package com.mars.blackcat.component;

import com.mars.blackcat.ui.activity.SettingActivity;
import com.mars.blackcat.ui.activity.WifiBookActivity;
import com.mars.blackcat.ui.fragment.RecommendFragment;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface MainComponent {
    RecommendFragment inject(RecommendFragment fragment);
    SettingActivity inject(SettingActivity activity);
    WifiBookActivity inject(WifiBookActivity activity);
}