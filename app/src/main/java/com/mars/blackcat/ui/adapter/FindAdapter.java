package com.mars.blackcat.ui.adapter;

import android.content.Context;
import android.view.View;

import com.mars.blackcat.R;
import com.mars.blackcat.bean.other.FindBean;
import com.mars.blackcat.common.OnRvItemClickListener;
import com.mars.blackcat.view.easyadapter.recyclerview.EasyRVAdapter;
import com.mars.blackcat.view.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * @author lfh.
 * @date 16/8/16.
 */
public class FindAdapter extends EasyRVAdapter<FindBean> {
    private OnRvItemClickListener itemClickListener;

    public FindAdapter(Context context, List<FindBean> list, OnRvItemClickListener
            listener) {
        super(context, list, R.layout.item_find);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(final EasyRVHolder holder, final int position, final FindBean item) {

        holder.setText(R.id.tvTitle, item.getTitle());
        holder.setImageResource(R.id.ivIcon,item.getIconResId());

        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(holder.getItemView(), position, item);
            }
        });
    }
}
