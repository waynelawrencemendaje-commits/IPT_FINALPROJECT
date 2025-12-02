package com.example.ipt_finalproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth; // Import FirebaseAuth
import de.hdodenhof.circleimageview.CircleImageView;

public class MainAccount extends AppCompatActivity {

    // Firebase
    private FirebaseAuth mAuth;

    private CircleImageView ivAvatar;
    private ActivityResultLauncher<Intent> galleryLauncher;

    // Buttons
    private Button btnUploadAvatar;
    private Button btnChangePassword;
    private Button btnLogout;
    private Button btnCancel;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        // --- INITIALIZATION ---
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Find all views
        ivAvatar = findViewById(R.id.iv_avatar);
        btnUploadAvatar = findViewById(R.id.btn_upload_avatar);
        btnChangePassword = findViewById(R.id.btn_change_password);
        btnLogout = findViewById(R.id.btn_logout); // <-- Find the logout button
        btnCancel = findViewById(R.id.btn_cancel);
        btnConfirm = findViewById(R.id.btn_confirm);

        // Close button (X) - Go back to Dashboard
        ImageView ivClose = findViewById(R.id.iv_close);
        ivClose.setOnClickListener(v -> finish());

        // --- EVENT LISTENERS ---

        // Setup gallery launcher for avatar upload
        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        if (imageUri != null) {
                            try {
                                getContentResolver().takePersistableUriPermission(
                                        imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            } catch (Exception e) {
                                // Ignore if permission not supported
                            }
                            ivAvatar.setImageURI(imageUri);
                        }
                    }
                });

        // Open gallery when avatar or upload button is clicked
        ivAvatar.setOnClickListener(v -> openGallery());
        btnUploadAvatar.setOnClickListener(v -> openGallery());

        // --- LOGOUT BUTTON LOGIC ---
        btnLogout.setOnClickListener(v -> {
            // Show a confirmation dialog before logging out
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // User clicked "Yes", so sign out
                        signOutUser();
                    })
                    .setNegativeButton("No", null) // "No" button does nothing
                    .show();
        });


        // Show change password popup
        btnChangePassword.setOnClickListener(v -> {
            findViewById(R.id.layout_main).setVisibility(View.GONE);
            findViewById(R.id.layout_change_password).setVisibility(View.VISIBLE);
        });

        // Cancel button - hide popup
        btnCancel.setOnClickListener(v -> {
            findViewById(R.id.layout_change_password).setVisibility(View.GONE);
            findViewById(R.id.layout_main).setVisibility(View.VISIBLE);
        });

        // Confirm button - show success and hide popup
        btnConfirm.setOnClickListener(v -> {
            // For a real app, you would add password change logic here
            Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_LONG).show();
            findViewById(R.id.layout_change_password).setVisibility(View.GONE);
            findViewById(R.id.layout_main).setVisibility(View.VISIBLE);
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryLauncher.launch(Intent.createChooser(intent, "Select Avatar"));
    }

    // --- SIGN OUT METHOD ---
    private void signOutUser() {
        // Sign out from Firebase
        mAuth.signOut();

        // Redirect user to the Login screen
        Intent intent = new Intent(MainAccount.this, MainLogin.class);

        // Clear the activity stack to prevent the user from returning to a logged-in state
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish(); // Finish the current activity (and any others in the task)
    }
}
