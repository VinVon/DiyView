package com.demo.diyview.progress;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.demo.diyview.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/7/25.
 */

public class ChangeActivity extends AppCompatActivity {
    @BindView(R.id.views)
    ChangeView views;
    private int count =1;
    private Timer timer;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    views.setType(ChangeView.Type.RECTANGLULAR);
                    break;
                case 2:
                    views.setType(ChangeView.Type.TRIANGLE);
                    break;
                case 3:
                    views.setType(ChangeView.Type.CIRCULAR);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_activity);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
       if (timer != null){
           timer.cancel();
       }
        timer = new Timer();
        timer.schedule(task,0,1000);
    }
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (count == 1){
                Message message = new Message();
                message.what = 1;count++;
                handler.sendMessage(message);
            }else  if (count==2){
                Message message = new Message();
                message.what = 2;count++;
                handler.sendMessage(message);
            }else{
                Message message = new Message();
                message.what = 3;count=1;
                handler.sendMessage(message);
            }
        }
    };
}
