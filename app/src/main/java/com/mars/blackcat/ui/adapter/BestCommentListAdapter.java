package com.mars.blackcat.ui.adapter;

import android.content.Context;
import android.view.View;

import com.mars.blackcat.R;
import com.mars.blackcat.base.Constant;
import com.mars.blackcat.bean.CommentList;
import com.mars.blackcat.common.OnRvItemClickListener;
import com.mars.blackcat.view.easyadapter.recyclerview.EasyRVAdapter;
import com.mars.blackcat.view.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

/**
 * @author lfh.
 * @date 16/9/2.
 */
public class BestCommentListAdapter extends EasyRVAdapter<CommentList.CommentsBean> {

    private OnRvItemClickListener listener;

    public BestCommentListAdapter(Context context, List<CommentList.CommentsBean> list) {
        super(context, list, R.layout.item_comment_best_list);
    }

    @Override
    protected void onBindData(final EasyRVHolder viewHolder, final int position, final CommentList.CommentsBean item) {viewHolder.setCircleImageUrl(R.id.ivBookCover, Constant.IMG_BASE_URL + item.author.avatar, R.mipmap.avatar_default)
                .setText(R.id.tvBookTitle, item.author.nickname)
                .setText(R.id.tvContent, item.content)
                .setText(R.id.tvBookType, String.format(mContext.getString(R.string.book_detail_user_lv), item.author.lv))
                .setText(R.id.tvFloor, String.format(mContext.getString(R.string.comment_floor), item.floor))
                .setText(R.id.tvLikeCount, String.format(mContext.getString(R.string.comment_like_count), item.likeCount));

        viewHolder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null)
                    listener.onItemClick(viewHolder.getItemView(), position, item);
            }
        });
    }

    public void setOnItemClickListener(OnRvItemClickListener listener){
        this.listener = listener;
    }
}
