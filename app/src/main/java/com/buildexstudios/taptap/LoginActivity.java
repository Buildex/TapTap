package com.buildexstudios.taptap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText et_login;
    private EditText et_password;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        et_login = findViewById(R.id.et_login);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);

        SharedPreferences sharedPref = getSharedPreferences("prefs1",
                Context.MODE_PRIVATE);

        String nickname = sharedPref.getString("nickname", "bot");
        String password = sharedPref.getString("password", "123");

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_password.getText().toString().isEmpty() || et_login.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, R.string.invalid_username, Toast.LENGTH_SHORT).show();
                } else {
                    if (et_login.getText().toString().equals(nickname)) {
                        if (et_password.getText().toString().equals(password)) {
                            Intent intent = new Intent(v.getContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.d("login", "Mot de passe non valide");
                            Toast.makeText(LoginActivity.this, R.string.invalid_password, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("login", "Login non valide");
                        Toast.makeText(LoginActivity.this, R.string.invalid_username, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}