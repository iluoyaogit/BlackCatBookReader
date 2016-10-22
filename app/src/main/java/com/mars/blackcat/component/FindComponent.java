package com.mars.blackcat.component;

import com.mars.blackcat.ui.activity.SubCategoryListActivity;
import com.mars.blackcat.ui.activity.SubRankActivity;
import com.mars.blackcat.ui.activity.SubjectBookListActivity;
import com.mars.blackcat.ui.activity.SubjectBookListDetailActivity;
import com.mars.blackcat.ui.activity.TopCategoryListActivity;
import com.mars.blackcat.ui.activity.TopRankActivity;
import com.mars.blackcat.ui.fragment.SubCategoryFragment;
import com.mars.blackcat.ui.fragment.SubRankFragment;
import com.mars.blackcat.ui.fragment.SubjectFragment;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface FindComponent {

    /** 分类 **/
    TopCategoryListActivity inject(TopCategoryListActivity activity);

    SubCategoryListActivity inject(SubCategoryListActivity activity);

    SubCategoryFragment inject(SubCategoryFragment fragment);

    /** 排行 **/
    TopRankActivity inject(TopRankActivity activity);

    SubRankActivity inject(SubRankActivity activity);

    SubRankFragment inject(SubRankFragment fragment);

    /** 主题书单 **/
    SubjectBookListActivity inject(SubjectBookListActivity subjectBookListActivity);

    SubjectFragment inject(SubjectFragment subjectFragment);

    SubjectBookListDetailActivity inject(SubjectBookListDetailActivity categoryListActivity);
}
