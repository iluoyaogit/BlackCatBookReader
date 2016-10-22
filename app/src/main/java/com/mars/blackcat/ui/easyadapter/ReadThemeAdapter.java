package com.mars.blackcat.ui.easyadapter;

import android.content.Context;

import com.mars.blackcat.R;
import com.mars.blackcat.bean.other.ReadTheme;
import com.mars.blackcat.manager.ThemeManager;
import com.mars.blackcat.utils.LogUtils;
import com.mars.blackcat.view.easyadapter.abslistview.EasyLVAdapter;
import com.mars.blackcat.view.easyadapter.abslistview.EasyLVHolder;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/9/23.
 */
public class ReadThemeAdapter extends EasyLVAdapter<ReadTheme> {

    private int selected = 0;

    public ReadThemeAdapter(Context context, List<ReadTheme> list, int selected) {
        super(context, list, R.layout.item_read_theme);
        this.selected = selected;
    }

    @Override
    public void convert(EasyLVHolder holder, int position, ReadTheme readTheme) {
        if (readTheme != null) {
            ThemeManager.setReaderTheme(readTheme.theme, holder.getView(R.id.ivThemeBg));
            if (selected == position) {
                holder.setVisible(R.id.ivSelected, true);
            } else {
                holder.setVisible(R.id.ivSelected, false);
            }
        }
    }

    public void select(int position) {
        selected = position;
        LogUtils.i("curtheme=" + selected);
        notifyDataSetChanged();
    }
}
