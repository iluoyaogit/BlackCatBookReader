package com.mars.blackcat.ui.adapter;

import android.content.Context;

import com.mars.blackcat.R;
import com.mars.blackcat.view.easyadapter.abslistview.EasyLVAdapter;
import com.mars.blackcat.view.easyadapter.abslistview.EasyLVHolder;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/9/8.
 */
public class SearchHistoryAdapter extends EasyLVAdapter<String> {

    public SearchHistoryAdapter(Context context, List<String> list) {
        super(context, list, R.layout.item_search_history);
    }

    @Override
    public void convert(EasyLVHolder holder, int position, String s) {
        holder.setText(R.id.tvTitle, s);
    }
}
