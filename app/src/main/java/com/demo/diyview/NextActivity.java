package com.demo.diyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by raytine on 2017/7/24.
 */

public class NextActivity extends AppCompatActivity {
    private Button btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn_2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("----","TAFnextactivity");
                startActivity(new Intent(NextActivity.this,MainActivity.class));
            }
        });
    }
}
