package com.mars.blackcat.view;

import android.graphics.Color;

import com.mars.blackcat.base.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyh.
 * @date 16/8/7.
 */
public class TagColor {

    public int borderColor = Color.parseColor("#49C120");
    public int backgroundColor = Color.parseColor("#49C120");
    public int textColor = Color.WHITE;

    public static List<TagColor> getRandomColors(int size){

        List<TagColor> list = new ArrayList<>();
        for(int i=0; i< size; i++){
            TagColor color = new TagColor();
            color.borderColor = color.backgroundColor = Constant.tagColors[i % Constant.tagColors.length];
            list.add(color);
        }
        return list;
    }
}
