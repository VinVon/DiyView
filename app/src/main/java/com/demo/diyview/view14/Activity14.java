package com.demo.diyview.view14;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.diyview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/8/4.
 */

public class Activity14 extends AppCompatActivity {
    @BindView(R.id.list)
    ListView list;
    private List<String> strings = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity14_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        for (int i = 0; i < 100; i++) {
            strings.add("条目"+i);
        }
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return strings.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View inflate = LayoutInflater.from(Activity14.this).inflate(R.layout.item14, null);
                TextView viewById = (TextView) inflate.findViewById(R.id.text14);
                viewById.setText(strings.get(position));
                return inflate;
            }
        });
    }
}
