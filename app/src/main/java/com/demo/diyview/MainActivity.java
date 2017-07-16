package com.demo.diyview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.diyview.view.MyTextView;

public class MainActivity extends AppCompatActivity {
    private MyTextView myTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTextView = (MyTextView) findViewById(R.id.text);
        myTextView.setOnCliCkhaha(new MyTextView.OnClickListener() {
            @Override
            public void OnClick(View v) {
                Toast.makeText(MainActivity.this, "fdsfs", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
