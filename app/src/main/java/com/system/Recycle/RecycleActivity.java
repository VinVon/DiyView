package com.system.Recycle;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.diyview.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/11/4.
 */

public class RecycleActivity extends AppCompatActivity implements GroupListener{
    @BindView(R.id.recycle)
    RecyclerView recycle;
    private List<City> citys = new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(this);
        recycle.setLayoutManager(linearLayoutManager);
        recycle.addItemDecoration(MyItemDecoration.Builder.init(this).setGroupBackground(Color.BLUE).setDivideHeight(1).build());
        recycle.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 5; i++) {
           citys.add(new City("浙江","杭州"+i));
        }
        for (int i = 0; i < 5; i++) {
            citys.add(new City("湖北","武汉"+i));
        }
        for (int i = 0; i < 5; i++) {
            citys.add(new City("湖北","仙桃"+i));
        }
        for (int i = 0; i < 5; i++) {
            citys.add(new City("重庆","四川"+i));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getGroupName(int position) {
        return citys.get(position).getProvince();
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private Context mContext;

        public MyAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent,false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            holder.v_name.setText(citys.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return citys.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView v_name;

            public ViewHolder(View itemView) {
                super(itemView);
                v_name = (TextView) itemView.findViewById(R.id.item_name);
            }
        }
    }
}
