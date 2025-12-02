package com.example.ipt_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainSignup extends AppCompatActivity {

    private EditText etSignUpEmail, etSignUpPassword, etConfirmPassword;
    private Button btnSignUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        etSignUpEmail = findViewById(R.id.signupemail);
        etSignUpPassword = findViewById(R.id.signuppassword);
        etConfirmPassword = findViewById(R.id.confirmpassword);
        btnSignUp = findViewById(R.id.sign_up_button);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSignUp.setOnClickListener(v -> registerUser());

        findViewById(R.id.sign_in_text).setOnClickListener(v -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    private void registerUser() {
        String email = etSignUpEmail.getText().toString().trim();
        String password = etSignUpPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            etSignUpEmail.setError("Email is required.");
            etSignUpEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etSignUpPassword.setError("Password is required.");
            etSignUpPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etSignUpPassword.setError("Password must be at least 6 characters.");
            etSignUpPassword.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("Please confirm your password.");
            etConfirmPassword.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match.");
            etConfirmPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        CustomToast.show(this, "Registration successful!", Toast.LENGTH_LONG);

                        Intent intent = new Intent(MainSignup.this, MainLogin.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        String errorMsg = task.getException() != null
                                ? task.getException().getMessage()
                                : "Unknown error";
                        CustomToast.show(this, "Sign up failed: " + errorMsg, Toast.LENGTH_LONG);
                    }
                });
    }
}
