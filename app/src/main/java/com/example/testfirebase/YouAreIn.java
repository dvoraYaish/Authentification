package com.example.testfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class YouAreIn extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;

    Button btLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_are_in);

        mAuth = FirebaseAuth.getInstance();

        btLogOut = findViewById(R.id.btLogOut);
        btLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btLogOut)
        {
            mAuth.signOut();
            startActivity(new Intent(YouAreIn.this, MainActivity.class ));
        }
    }
}