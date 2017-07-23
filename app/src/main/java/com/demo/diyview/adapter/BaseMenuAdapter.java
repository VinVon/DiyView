package com.demo.diyview.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 123 on 2017/7/23.
 */

public abstract class BaseMenuAdapter {
    public abstract int getCount();
    public abstract View getTabView(int position, ViewGroup parent);
    public abstract View getMenuView(int position, ViewGroup parent);
}
