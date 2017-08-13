package com.demo.diyview.view17;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/8/10.
 */

public class Activity17 extends AppCompatActivity {
    @BindView(R.id.titleBar)
    LinearLayout titleBar;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.img)
    ImageView img;
    private int imgHeight;
    private int statusHeight;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity17_main);
        ButterKnife.bind(this);
        StatusBarUtil.setActivityTranslucent(this);
        titleBar.getBackground().setAlpha(0);
        img.post(new Runnable() {
            @Override
            public void run() {
                imgHeight = img.getMeasuredHeight();
                statusHeight = titleBar.getMeasuredHeight();
            }
        });
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    float alpha = (float) scrollY/(imgHeight-statusHeight);
                Log.e("TAG",scrollY+"-->"+alpha);
                if (alpha>1){
                    alpha = 1;
                }
                if (alpha<0){
                    alpha = 0;
                }
                titleBar.getBackground().setAlpha((int) (alpha*255));
            }
        });
    }
}
