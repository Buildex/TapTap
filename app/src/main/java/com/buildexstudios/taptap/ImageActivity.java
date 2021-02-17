package com.buildexstudios.taptap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ImageActivity extends AppCompatActivity {

    private TextView tv_score_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        long score = getIntent().getLongExtra("score", 0);

        tv_score_image = findViewById(R.id.tv_score_image);
        tv_score_image.setText(String.format(getResources().getString(R.string.ms), score));
    }
}