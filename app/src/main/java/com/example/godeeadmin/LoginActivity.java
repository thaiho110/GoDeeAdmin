package com.example.godeeadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        TextInputEditText username  = findViewById(R.id.usernameSignIn);
        TextInputEditText password = findViewById(R.id.passwordSignIn);

        String user = String.valueOf(username.getText());
        String pass = String.valueOf(password.getText());

        Button adminLogin = findViewById(R.id.btn_login_admin);
        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((username.getText().toString().equals("admin")) && (password.getText().toString().equals("admin123"))) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}