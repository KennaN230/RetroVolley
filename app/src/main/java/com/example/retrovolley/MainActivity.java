// MainActivity.java
package com.example.retrovolley;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.Login);
        TextView textView = findViewById(R.id.registerTextView);

        String belumPunyaAkunText = "Belum mempunyai akun? ";
        String registerSekarangText = "Register sekarang!";

        SpannableString spannableString = new SpannableString(belumPunyaAkunText + registerSekarangText);

        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, belumPunyaAkunText.length(), 0);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Navigate to the Register activity
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        };

        spannableString.setSpan(clickableSpan, belumPunyaAkunText.length(), spannableString.length(), 0);

        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Use the LoginTask to handle the login process
                new LoginTask(MainActivity.this).execute(username, password);
            }
        });
    }
}
