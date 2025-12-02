package com.example.ipt_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainLogin extends AppCompatActivity {

    // Declare UI elements and FirebaseAuth instance
    private EditText etEmail, etPassword;
    private TextView btnSignIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize UI elements
        etEmail = findViewById(R.id.signinemail);
        etPassword = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btn_sign_in);

        // Padding for status bar / navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Sign-up text
        TextView tvSignUpNow = findViewById(R.id.tv_sign_up_now);
        tvSignUpNow.setOnClickListener(v -> {
            startActivity(new Intent(MainLogin.this, MainSignup.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // SIGN IN BUTTON LOGIC
        btnSignIn.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // Validate input fields
            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email is required.");
                etEmail.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Password is required.");
                etPassword.requestFocus();
                return;
            }

            // Sign in with Firebase
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("FirebaseAuth", "signInWithEmail:success");
                            Toast.makeText(MainLogin.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FirebaseAuth", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    });
        });// OLD
// Toast.makeText(MainLogin.this, "Login Successful.", Toast.LENGTH_SHORT).show();

// NEW
        CustomToast.show(MainLogin.this, "Login Successful.", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // User is signed in, navigate to the dashboard
            Intent intent = new Intent(MainLogin.this, MainDashBoard.class);
            startActivity(intent);
            finish(); // This removes Login from back stack
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
