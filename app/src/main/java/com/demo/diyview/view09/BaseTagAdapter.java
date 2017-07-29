package com.demo.diyview.view09;

import android.view.View;
import android.view.ViewGroup;

/**
 * 流式布局adapter设计模式
 * Created by raytine on 2017/7/29.
 */

public abstract class BaseTagAdapter {
    //有多少条目
    public abstract int getCount();
    public abstract View getView(int postion, ViewGroup parent);
}
