package com.buildexstudios.taptap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {

    private EditText et_pseudo;
    private Button bt_reset;
    private Button bt_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPref = getSharedPreferences("prefs1",
                Context.MODE_PRIVATE);

        et_pseudo = findViewById(R.id.et_pseudo);
        bt_reset = findViewById(R.id.bt_reset);
        bt_share = findViewById(R.id.bt_share);

        et_pseudo.setText(sharedPref.getString("nickname", "bot"));

        et_pseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    sharedPref.edit().putString("nickname", String.valueOf(s)).apply();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.edit().putLong("best_score", 0).apply();
            }
        });

        bt_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:");
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", String.format(getResources().getString(R.string.share_score_body), getIntent().getLongExtra("best_score", 0)));
                startActivity(it);
            }
        });
    }
}