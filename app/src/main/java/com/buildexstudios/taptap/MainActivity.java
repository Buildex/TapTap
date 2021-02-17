package com.buildexstudios.taptap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bt_settings;
    private Button bt_go;
    private TextView tv_score;
    private Button bt_jeu1;
    private Button bt_jeu2;
    private Button bt_jeu3;
    private Button bt_jeu4;
    private TextView tv_joueur;
    private long timer;
    private long msDebut;
    private int index_button;
    private long best_score;
    private final int REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_settings = findViewById(R.id.bt_settings);
        bt_go = findViewById(R.id.bt_go);
        tv_score = findViewById(R.id.tv_score);
        bt_jeu1 = findViewById(R.id.bt_jeu1);
        bt_jeu2 = findViewById(R.id.bt_jeu2);
        bt_jeu3 = findViewById(R.id.bt_jeu3);
        bt_jeu4 = findViewById(R.id.bt_jeu4);
        tv_joueur = findViewById(R.id.tv_joueur);

        index_button = 1;

        SharedPreferences sharedPref = getSharedPreferences("prefs1",
                Context.MODE_PRIVATE);
        best_score = sharedPref.getLong("best_score", 0);
        if (best_score == 0) {
            tv_score.setText("");
        }
        tv_score.setText(String.valueOf(best_score));
        tv_joueur.setText(String.format(getResources().getString(R.string.player), sharedPref.getString("nickname", "bot")));

        bt_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SettingsActivity.class);
                intent.putExtra("best_score", best_score);
                startActivity(intent);
            }
        });

        bt_jeu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index_button == 1) {
                    timer = System.currentTimeMillis();
                    msDebut = timer;
                    bt_jeu1.setEnabled(false);
                    Log.d("jeu", "Timer démarré");
                    index_button++;
                }
            }
        });

        bt_jeu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index_button == 2) {
                    timer = System.currentTimeMillis() - msDebut;
                    bt_jeu2.setEnabled(false);
                    bt_jeu2.setText(String.format(getResources().getString(R.string.ms), timer));
                    Log.d("jeu", "Deuxième case touchée");
                    index_button++;
                }
            }
        });

        bt_jeu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index_button == 3) {
                    timer = System.currentTimeMillis() - msDebut;
                    bt_jeu3.setEnabled(false);
                    bt_jeu3.setText(String.format(getResources().getString(R.string.ms), timer));
                    Log.d("jeu", "Troisième case touchée");
                    index_button++;
                }
            }
        });

        bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index_button = 1;
                bt_jeu1.setEnabled(true);
                bt_jeu2.setEnabled(true);
                bt_jeu3.setEnabled(true);
                bt_jeu4.setEnabled(true);
                bt_jeu1.setText(R.string.one);
                bt_jeu2.setText(R.string.two);
                bt_jeu3.setText(R.string.three);
                bt_jeu4.setText(R.string.four);
            }
        });

        bt_jeu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index_button == 4) {
                    timer = System.currentTimeMillis() - msDebut;
                    bt_jeu4.setEnabled(false);
                    bt_jeu4.setText(String.format(getResources().getString(R.string.ms), timer));
                    Log.d("jeu", "Quatrième case touchée");

                    if (timer >= 1000 && timer <= 2000) {
                        Intent intent = new Intent(v.getContext(), ImageActivity.class);
                        intent.putExtra("score", timer);
                        startActivity(intent);
                    }

                    if (best_score > timer || best_score == 0) {
                        Log.d("test", String.valueOf(timer));
                        Log.d("test", String.valueOf(best_score));
                        tv_score.setText(String.valueOf(timer));
                        best_score = timer;
                        sharedPref.edit().putLong("best_score", timer).apply();
                    }
                }
            }
        });
    }
}