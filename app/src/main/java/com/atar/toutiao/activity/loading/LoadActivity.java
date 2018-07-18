package com.atar.toutiao.activity.loading;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.atar.toutiao.R;
import com.atar.toutiao.activity.MainActivity;

public class LoadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        startActivity(new Intent(this, MainActivity.class));
    }
}
