package com.mars.blackcat.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.rollviewpager.Util;
import com.mars.blackcat.R;
import com.mars.blackcat.base.BaseFragment;
import com.mars.blackcat.bean.other.FindBean;
import com.mars.blackcat.common.OnRvItemClickListener;
import com.mars.blackcat.component.AppComponent;
import com.mars.blackcat.ui.activity.SubjectBookListActivity;
import com.mars.blackcat.ui.activity.TopCategoryListActivity;
import com.mars.blackcat.ui.activity.TopRankActivity;
import com.mars.blackcat.ui.adapter.FindAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 发现
 *
 * @author yuyh.
 * @date 16/9/1.
 */
public class FindFragment extends BaseFragment implements OnRvItemClickListener<FindBean> {

    @Bind(R.id.recyclerview)
    EasyRecyclerView mRecyclerView;

    private FindAdapter mAdapter;
    private List<FindBean> mList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_find;
    }

    @Override
    public void initDatas() {
        mList.clear();
        mList.add(new FindBean("排行榜", R.mipmap.home_find_rank));
        mList.add(new FindBean("主题书单", R.mipmap.home_find_topic));
        mList.add(new FindBean("分类", R.mipmap.home_find_category));
        mList.add(new FindBean("有声小说", R.mipmap.home_find_listen));
    }

    @Override
    public void configViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerDecoration itemDecoration = new DividerDecoration(getResources().getColor(R.color.common_divider_narrow), Util.dip2px(getContext(),0.5f), Util.dip2px(getContext(),72),0);
        itemDecoration.setDrawLastItem(false);
        mRecyclerView.addItemDecoration(itemDecoration);
        mAdapter = new FindAdapter(mContext, mList, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void attachView() {

    }

    @Override
    public void onItemClick(View view, int position, FindBean data) {
        switch (position) {
            case 0:
                TopRankActivity.startActivity(activity);
                break;
            case 1:
                SubjectBookListActivity.startActivity(activity);
                break;
            case 2:
                startActivity(new Intent(activity, TopCategoryListActivity.class));
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    public static FindFragment getInstance(String title) {
        FindFragment sf = new FindFragment();
        return sf;
    }

}
