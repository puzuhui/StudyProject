package com.example.admin.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

/**
 * Created by Admin on 2018/1/2.
 * 作者：蒲祖辉
 * 电话：15823903420
 */

public class MainActivity extends Activity {
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("中国");
        toolbar.setSubtitle("厉害");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("====", "点击 " );
            }
        });
    }
}
