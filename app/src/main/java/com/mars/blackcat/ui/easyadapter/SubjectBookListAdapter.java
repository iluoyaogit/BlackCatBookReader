package com.mars.blackcat.ui.easyadapter;

import android.content.Context;
import android.view.ViewGroup;

import com.mars.blackcat.R;
import com.mars.blackcat.base.Constant;
import com.mars.blackcat.bean.BookLists;
import com.mars.blackcat.manager.SettingManager;
import com.mars.blackcat.view.recyclerview.adapter.BaseViewHolder;
import com.mars.blackcat.view.recyclerview.adapter.RecyclerArrayAdapter;

/**
 * 主题书单
 *
 * @author yuyh.
 * @date 16/9/3.
 */
public class SubjectBookListAdapter extends RecyclerArrayAdapter<BookLists.BookListsBean> {
    public SubjectBookListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder<BookLists.BookListsBean>(parent, R.layout.item_sub_category_list) {
            @Override
            public void setData(BookLists.BookListsBean item) {
                super.setData(item);
                if (!SettingManager.getInstance().isNoneCover()) {
                    holder.setRoundImageUrl(R.id.ivSubCateCover, Constant.IMG_BASE_URL + item.cover,
                            R.mipmap.cover_default);
                } else {
                    holder.setImageResource(R.id.ivSubCateCover, R.mipmap.cover_default);
                }

                holder.setText(R.id.tvSubCateTitle, item.title)
                        .setText(R.id.tvSubCateAuthor, item.author)
                        .setText(R.id.tvSubCateShort, item.desc)
                        .setText(R.id.tvSubCateMsg, String.format(mContext.getResources().getString(R.string.subject_book_msg), item.bookCount, item.collectorCount));
            }
        };
    }
}
