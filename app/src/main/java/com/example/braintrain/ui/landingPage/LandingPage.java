package com.example.braintrain.ui.landingPage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.braintrain.ui.login.LoginActivity;
import com.example.braintrain.R;

public class LandingPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LandingPage.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, 5000);
    }
}
