package com.demo.diyview.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.diyview.R;
import com.demo.diyview.docDemo.SlideTab;

/**
 * Created by raytine on 2017/10/13.
 */

public class ColorTrackActivity extends AppCompatActivity {
    String[] strs = new String[]{"体育", "娱乐", "电竞", "游戏", "体育1", "娱乐1", "电竞1", "游戏1", "体育2", "娱乐2", "电竞2", "游戏2", "体育3", "娱乐3", "电竞3", "游戏3"};
    private SlideTab st;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_main);
        st = (SlideTab) findViewById(R.id.demo);
        st.setStrs(strs);
        st.setmTextOnclickListener(new SlideTab.textOclickListener() {
            @Override
            public void click(View view, int position) {
                st.setmCurrentColor(position);
                Toast.makeText(ColorTrackActivity.this,((TextView)view).getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}
