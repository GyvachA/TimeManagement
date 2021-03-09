package com.management.timemanagement.ui.authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.management.timemanagement.MainActivity;
import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.data.local.SessionManager;

public class LoginActivity extends AppCompatActivity {

    SessionManager sessionManager;

    TextInputEditText login_et;
    TextInputEditText password_et;

    Button login_btn;
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_et = findViewById(R.id.login_et);
        password_et = findViewById(R.id.password_et);
        login_btn = findViewById(R.id.enter_button);
        register_btn = findViewById(R.id.register_btn);

        sessionManager = new SessionManager(getApplicationContext());

        if (sessionManager.isLoggedIn()) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = login_et.getText().toString();
                String password = password_et.getText().toString();

                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                checkLogin(login, password);
            }

        });
    }

    private void checkLogin(String login, String password) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();
        Cursor c = db.getUserByLoginAndPassword(login, password);
        if (!c.moveToNext()) {
            Toast.makeText(getApplicationContext(), "Неправильный логин или пароль", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        db.closeDB();
        sessionManager.setLogin(true);
        sessionManager.setNickname(login);

        Log.d("LOGIN", login + "|" + sessionManager.getLogin());

        if (login.equals("Модератор")) {
            sessionManager.setModer(true);
        } else {
            sessionManager.setModer(false);
        }
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}