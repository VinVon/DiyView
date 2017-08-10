package com.demo.diyview.view15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.demo.diyview.R;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raytine on 2017/8/8.
 */

public class Activity15 extends AppCompatActivity {
    @BindView(R.id.lock)
    LockParentViews lock;
    private String pass = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity15_main);
        ButterKnife.bind(this);
        lock.setCallBack(new LockParentViews.CallBack() {
            @Override
            public void callback(String password) {
                if (pass == null){
                    pass = password;
                    Toast.makeText(Activity15.this,"记住密码",Toast.LENGTH_SHORT).show();
                    lock.clearPoint();
                }else{
                    if (pass.equals(password)){
                        Toast.makeText(Activity15.this,"密码正确",Toast.LENGTH_SHORT).show();
                        lock.clearPoint();
                    }else{
                        Toast.makeText(Activity15.this,"密码错误",Toast.LENGTH_SHORT).show();
                        lock.showError();
                    }
                }
            }
        });
    }
}
