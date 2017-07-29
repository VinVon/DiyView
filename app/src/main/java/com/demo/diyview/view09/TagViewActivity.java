package com.demo.diyview.view09;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.diyview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/7/29.
 */

public class TagViewActivity extends AppCompatActivity {
    @BindView(R.id.tag_view)
    TagLayout tagView;
    private List<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tagview_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        strings.add("苍井空");
        strings.add("苍井空1");
        strings.add("苍井空2");
        strings.add("苍井空3");
        strings.add("苍井空4");
        strings.add("苍井空5");
        tagView.setmBaseTagAdapter(new BaseTagAdapter() {
            @Override
            public int getCount() {
                return strings.size();
            }

            @Override
            public View getView(final int postion, ViewGroup parent) {
                TextView inflate = (TextView) getLayoutInflater().inflate(R.layout.tag_items,parent,false);
                inflate.setText(strings.get(postion));

                inflate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(TagViewActivity.this, strings.get(postion), Toast.LENGTH_SHORT).show();
                    }
                });
                return inflate;
            }
        });
    }
}
