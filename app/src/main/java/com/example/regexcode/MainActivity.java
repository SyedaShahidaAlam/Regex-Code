package com.example.regexcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, emailEditText, phoneEditText, passwordEditText, confirmPasswordEditText;
    private Button registerButton, viewDetailsButton;
    private TextView detailsTextView;
    private String username, email, phone;


    private static final Pattern USERNAME_PATTERN = Pattern.compile("[a-zA-Z0-9\\S]{4,20}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-z0-9._%+-]+@[a-z0-9.]{6,}");
    private static final Pattern PHONE_PATTERN = Pattern.compile("[0-9]{11}");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])[\\d\\D]{6,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmpass);
        registerButton = findViewById(R.id.regBtn);
        viewDetailsButton = findViewById(R.id.viewDetailsBtn);
        detailsTextView = findViewById(R.id.detailsTextView);

        viewDetailsButton.setVisibility(View.GONE);
        detailsTextView.setVisibility(View.GONE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                email = emailEditText.getText().toString();
                phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (username.isEmpty())
                {
                    usernameEditText.setError("Empty!!");
                    usernameEditText.requestFocus();
                }

                else if (!USERNAME_PATTERN.matcher(username).matches())
                {
                    usernameEditText.setError("Username must be 4-20 characters with no spaces.");
                    usernameEditText.requestFocus();
                }

                else if (email.isEmpty())
                {
                    emailEditText.setError("Empty!!");
                    emailEditText.requestFocus();
                }


                else if (!EMAIL_PATTERN.matcher(email).matches())
                {
                    emailEditText.setError("Invalid email format.");
                    emailEditText.requestFocus();
                }

                else if (phone.isEmpty())
                {
                    phoneEditText.setError("Empty!!");
                    phoneEditText.requestFocus();
                }

                else if (!PHONE_PATTERN.matcher(phone).matches())
                {
                    phoneEditText.setError("Phone number must be 11 digits.");
                    phoneEditText.requestFocus();
                }

                else if (password.isEmpty())
                {
                    passwordEditText.setError("Empty!!");
                    passwordEditText.requestFocus();
                }

                else if (!PASSWORD_PATTERN.matcher(password).matches())
                {
                    passwordEditText.setError("Password must be at least 6 characters and contain letters.");
                    passwordEditText.requestFocus();
                }

                else if (confirmPassword.isEmpty())
                {
                    confirmPasswordEditText.setError("Empty!!");
                    confirmPasswordEditText.requestFocus();
                }

                else if (!password.equals(confirmPassword))
                {
                    confirmPasswordEditText.setError("Passwords do not match.");
                    confirmPasswordEditText.requestFocus();
                }

                else
                {
                    Toast.makeText(MainActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    viewDetailsButton.setVisibility(View.VISIBLE);
                }
            }
        });

        viewDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String details = "User Details:\n\nUsername: " + username + "\nEmail: " + email + "\nPhone: " + phone;
                detailsTextView.setText(details);
                detailsTextView.setVisibility(View.VISIBLE);
            }
        });
    }
}



