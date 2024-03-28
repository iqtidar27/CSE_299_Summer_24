package com.example.stepcal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.stepcal.Activity.Login;
import com.example.stepcal.Activity.SignUp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void signUp(View view) {
        Intent intent=new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }

    public void logIn(View view) {
        Intent intent=new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
}