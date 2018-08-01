package com.gu.toolargetool.sample;

import android.os.Bundle;

import com.gu.toolargetool.TooLargeTool;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String KEY = "TTLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new TooLargeTool().startLogging(this.getApplication());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            text.append("t");
        }
        outState.putString(KEY, text.toString());
        super.onSaveInstanceState(outState);
    }

}
