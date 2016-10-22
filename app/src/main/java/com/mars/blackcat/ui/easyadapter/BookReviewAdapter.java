package com.mars.blackcat.ui.easyadapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;

import com.mars.blackcat.R;
import com.mars.blackcat.base.Constant;
import com.mars.blackcat.bean.BookReviewList;
import com.mars.blackcat.manager.SettingManager;
import com.mars.blackcat.utils.FormatUtils;
import com.mars.blackcat.view.recyclerview.adapter.BaseViewHolder;
import com.mars.blackcat.view.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * @author lfh.
 * @date 16/9/3.
 */
public class BookReviewAdapter extends RecyclerArrayAdapter<BookReviewList.ReviewsBean> {


    public BookReviewAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<BookReviewList.ReviewsBean>(parent, R.layout.item_community_book_review_list) {
            @Override
            public void setData(BookReviewList.ReviewsBean item) {
                if (!SettingManager.getInstance().isNoneCover()) {
                    holder.setRoundImageUrl(R.id.ivBookCover, Constant.IMG_BASE_URL + item.book.cover,
                            R.mipmap.cover_default);
                } else {
                    holder.setImageResource(R.id.ivBookCover, R.mipmap.cover_default);
                }

                holder.setText(R.id.tvBookTitle, item.book.title)
                        .setText(R.id.tvBookType, String.format(mContext.getString(R.string.book_review_book_type), Constant.bookType.get(item.book.type)))
                        .setText(R.id.tvTitle, item.title)
                        .setText(R.id.tvHelpfulYes, String.format(mContext.getString(R.string.book_review_helpful_yes), item.helpful.yes));

                if (TextUtils.equals(item.state, "hot")) {
                    holder.setVisible(R.id.tvHot, true);
                    holder.setVisible(R.id.tvTime, false);
                    holder.setVisible(R.id.tvDistillate, false);
                } else if (TextUtils.equals(item.state, "distillate")) {
                    holder.setVisible(R.id.tvDistillate, true);
                    holder.setVisible(R.id.tvHot, false);
                    holder.setVisible(R.id.tvTime, false);
                } else {
                    holder.setVisible(R.id.tvTime, true);
                    holder.setVisible(R.id.tvHot, false);
                    holder.setVisible(R.id.tvDistillate, false);
                    holder.setText(R.id.tvTime, FormatUtils.formatDate(item.created));
                }
            }
        };
    }
}
