package com.management.timemanagement.ui.authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.management.timemanagement.MainActivity;
import com.management.timemanagement.R;
import com.management.timemanagement.data.local.DBAdapter;
import com.management.timemanagement.data.local.SessionManager;

public class RegisterActivity extends AppCompatActivity {

    SessionManager sessionManager;

    TextInputEditText login_reg_et;
    TextInputEditText password_reg_et;


    Button login_reg_btn;
    Button tologin_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sessionManager = new SessionManager(getApplicationContext());

        login_reg_et = findViewById(R.id.login_reg_et);
        password_reg_et = findViewById(R.id.password_reg_et);

        login_reg_btn = findViewById(R.id.reg_enter_button);
        tologin_btn = findViewById(R.id.tologin_btn);

        tologin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        login_reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = login_reg_et.getText().toString();
                String password = password_reg_et.getText().toString();

                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT)
                    .show();
                    return;
                } else if (login.length() > 50 || login.length() < 3 || password.length() > 20 || password.length() < 5) {
                    Toast.makeText(getApplicationContext(), "Никнейм 4-50 символов, пароль 6-20 символов", Toast.LENGTH_SHORT)
                            .show();
                    return;
                }

                register(login, password);
            }
        });
    }

    private void register(String login, String password) {
        DBAdapter db = new DBAdapter(getApplicationContext());

        db.openDB();

        db.addUser(login, password);

        db.closeDB();

        sessionManager.setNickname(login);

        sessionManager.setModer(false);

        sessionManager.setLogin(true);

        if (login.equals("Модератор")) {
            sessionManager.setModer(true);
        }

        Intent i = new Intent(RegisterActivity.this, MainActivity.class);

        startActivity(i);

        finish();
    }
}