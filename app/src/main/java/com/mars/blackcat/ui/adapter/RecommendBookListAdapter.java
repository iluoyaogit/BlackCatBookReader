package com.mars.blackcat.ui.adapter;

import android.content.Context;
import android.view.View;

import com.mars.blackcat.R;
import com.mars.blackcat.base.Constant;
import com.mars.blackcat.bean.RecommendBookList;
import com.mars.blackcat.common.OnRvItemClickListener;
import com.mars.blackcat.manager.SettingManager;
import com.mars.blackcat.utils.NoDoubleClickListener;
import com.mars.blackcat.view.easyadapter.recyclerview.EasyRVAdapter;
import com.mars.blackcat.view.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * @author lfh.
 * @date 16/8/7.
 */
public class RecommendBookListAdapter extends EasyRVAdapter<RecommendBookList.RecommendBook> {

    private OnRvItemClickListener itemClickListener;

    public RecommendBookListAdapter(Context context, List<RecommendBookList.RecommendBook> list,
                                    OnRvItemClickListener listener) {
        super(context, list, R.layout.item_book_detail_recommend_book_list);
        this.itemClickListener = listener;
    }

    @Override
    protected void onBindData(final EasyRVHolder holder, final int position, final RecommendBookList.RecommendBook item) {
        if (!SettingManager.getInstance().isNoneCover()) {
            holder.setRoundImageUrl(R.id.ivBookListCover, Constant.IMG_BASE_URL + item.cover, R.mipmap.cover_default);
        }

        holder.setText(R.id.tvBookListTitle, item.title)
                .setText(R.id.tvBookAuthor, item.author)
                .setText(R.id.tvBookListTitle, item.title)
                .setText(R.id.tvBookListDesc, item.desc)
                .setText(R.id.tvBookCount, String.format(mContext.getString(R.string
                        .book_detail_recommend_book_list_book_count), item.bookCount))
                .setText(R.id.tvCollectorCount, String.format(mContext.getString(R.string
                        .book_detail_recommend_book_list_collector_count), item.collectorCount));
        holder.setOnItemViewClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                itemClickListener.onItemClick(holder.getItemView(), position, item);
            }
        });
    }

}