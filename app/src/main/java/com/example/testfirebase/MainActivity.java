package com.example.testfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText etEMail;
    EditText etPassword;
    Button btSignIn;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        etEMail = findViewById(R.id.editTextEmailAddress);
        etPassword = findViewById(R.id.editTextPassword);
        btSignIn = findViewById(R.id.buttonSignIn);
        btRegister = findViewById(R.id.buttonRegister);

        btSignIn.setOnClickListener(this);
        btRegister.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void onClick(View view) {
        if (view == btSignIn) {
            String email = etEMail.getText().toString();
            String password = etPassword.getText().toString();
            if (TextUtils.isEmpty(email)) {
                etEMail.setError("Email cannot be empty");
                etEMail.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                etPassword.setError("Password cannot be empty");
                etPassword.requestFocus();
            } else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "SignIn successed", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, YouAreIn.class));
                        } else {

                            Toast.makeText(MainActivity.this, "SignIn failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });


            }
        } else if (view == btRegister) {
            String email = etEMail.getText().toString();
            String password = etPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                etEMail.setError("Email cannot be empty");
                etEMail.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                etPassword.setError("Password cannot be empty");
                etPassword.requestFocus();
            } else {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Registration successed", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }
    }
}