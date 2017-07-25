package com.demo.diyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.demo.diyview.view.ColorTrackTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 字体变色效果测试activity
 * Created by raytine on 2017/7/25.
 */

public class ColorTrackActivity extends AppCompatActivity {


    @BindView(R.id.track_linear)
    LinearLayout trackLinear;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private String[] strs= {"体育","休闲","动漫"};
    private MyAdapter adapter;
    private List<View> views = new ArrayList<>();
    private List<ColorTrackTextView> viewss = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.color_track_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        for (int i = 0; i <strs.length ; i++) {
            ColorTrackTextView c = new ColorTrackTextView(this);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            p.weight=1;
            c.setLayoutParams(p);
            c.setText(strs[i]);
            trackLinear.addView(c);
            viewss.add(c);
        }
        views.add(getLayoutInflater().inflate(R.layout.layout1, null));
        views.add(getLayoutInflater().inflate(R.layout.layout2, null));
        views.add(getLayoutInflater().inflate(R.layout.layout3, null));
        adapter = new MyAdapter(views);
        viewpager.setAdapter(adapter);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("----viewpager","position : "+position);
                ColorTrackTextView c = viewss.get(position);
                c.setmDireciton(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
                c.setmCurrentProgress(1-positionOffset);
                try{
                ColorTrackTextView c1 = viewss.get(position+1);
                c1.setmDireciton(ColorTrackTextView.Direction.LRFT_TO_RIFHT);
                c1.setmCurrentProgress(positionOffset);
                }catch (Exception e){}
            }

            @Override
            public void onPageSelected(int position) {
//                Log.e("----onPageSelected","state : "+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.e("----onPageScroll","state : "+state);
            }
        });

    }
    class MyAdapter extends PagerAdapter{
        List<View> viewLists;

        public MyAdapter(List<View> lists)
        {
            viewLists = lists;
        }

        @Override
        public int getCount() {
            return viewLists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public void destroyItem(View view, int position, Object object)                       //销毁Item
        {
            ((ViewPager) view).removeView(viewLists.get(position));
        }

        @Override
        public Object instantiateItem(View view, int position)                                //实例化Item
        {
            ((ViewPager) view).addView(viewLists.get(position), 0);
            return viewLists.get(position);
        }
    }


}
