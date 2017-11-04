package com.demo.diyview.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.diyview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/8/15.
 */

public class TestActivity extends AppCompatActivity {


    @BindView(R.id.text_view)
    TestView textView;
    private List<Bean> been = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textactivity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Bean bean = new Bean("2017-07-16", 1.0);
        Bean bean1 = new Bean("2017-07-16", 22.0);
        Bean bean2 = new Bean("2017-07-16", 3.0);
        Bean bean3 = new Bean("2017-07-16", 44.0);
        been.add(bean);
        been.add(bean1);
        been.add(bean2);
        been.add(bean3);
        textView.addList(been);
    }
}
