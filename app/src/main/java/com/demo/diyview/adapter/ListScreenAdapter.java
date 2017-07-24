package com.demo.diyview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.diyview.R;

/**
 * Created by 123 on 2017/7/23.
 */

public class ListScreenAdapter extends BaseMenuAdapter {
    private String[] mItens = {"类型","品牌","价格","更多"};
    private Context mContent;
    public ListScreenAdapter(Context context) {
        this.mContent = context;
    }

    @Override
    public int getCount() {
        return mItens.length;
    }

    @Override
    public View getTabView(int position, ViewGroup parent) {
        TextView tabView  = (TextView) LayoutInflater.from(mContent).inflate(R.layout.tab_view, parent, false);
        tabView.setText(mItens[position]);
        return tabView;
    }

    @Override
    public View getMenuView(int position, ViewGroup parent) {
        TextView menuView  = (TextView) LayoutInflater.from(mContent).inflate(R.layout.menu_view, parent, false);
        menuView.setText(mItens[position]);
        return menuView;
    }
}
