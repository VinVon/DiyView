package com.demo.diyview.view07;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.demo.diyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/7/27.
 */

public class LetterActivity extends AppCompatActivity {
    @BindView(R.id.letter_view)
    LetterSideBar letterView;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.letter_activity);
        ButterKnife.bind(this);
        letterView.setTextOnclick(new LetterSideBar.TextOnclick() {
            @Override
            public void onclick(String st,boolean istouch) {
                if (istouch){
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(st);
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setVisibility(View.GONE);
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }
}
