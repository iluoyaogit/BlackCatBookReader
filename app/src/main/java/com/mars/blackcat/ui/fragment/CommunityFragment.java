package com.mars.blackcat.ui.fragment;

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
import com.mars.blackcat.ui.activity.BookDiscussionActivity;
import com.mars.blackcat.ui.activity.BookHelpActivity;
import com.mars.blackcat.ui.activity.BookReviewActivity;
import com.mars.blackcat.ui.activity.GirlBookDiscussionActivity;
import com.mars.blackcat.ui.adapter.FindAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class CommunityFragment extends BaseFragment implements OnRvItemClickListener<FindBean> {

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
        mList.add(new FindBean("综合讨论区", R.mipmap.discuss_section));
        mList.add(new FindBean("书评区", R.mipmap.comment_section));
        mList.add(new FindBean("书荒互助区", R.mipmap.helper_section));
        mList.add(new FindBean("女生区", R.mipmap.girl_section));
        mList.add(new FindBean("原创区",R.mipmap.yuanchuang));
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
                BookDiscussionActivity.startActivity(activity,true);
                break;
            case 1:
                BookReviewActivity.startActivity(activity);
                break;
            case 2:
                BookHelpActivity.startActivity(activity);
                break;
            case 3:
                GirlBookDiscussionActivity.startActivity(activity);
                break;
            case 4:
                BookDiscussionActivity.startActivity(activity,false);
                break;
            default:
                break;
        }
    }

    public static CommunityFragment getInstance(String title) {
        CommunityFragment sf = new CommunityFragment();
        return sf;
    }

}
