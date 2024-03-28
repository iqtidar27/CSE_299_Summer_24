package com.example.stepcal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stepcal.DTO.User;
import com.example.stepcal.R;


public class ActivityLevel extends AppCompatActivity {

    private RadioGroup activityLevelRadioGroup;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        activityLevelRadioGroup = findViewById(R.id.activityLevelRadioGroup);
        submitButton = findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton lowRadioButton = findViewById(R.id.low);
                RadioButton moderateRadioButton = findViewById(R.id.moderate);
                RadioButton highRadioButton = findViewById(R.id.high);
                RadioButton extremeRadioButton = findViewById(R.id.extreme);
                String level="";
               if(lowRadioButton.isChecked()){
                   level="low";
               }
               else if(moderateRadioButton.isChecked()){
                   level="moderate";
               }
               else if(highRadioButton.isChecked()){
                   level="high";
               }
               else if(extremeRadioButton.isChecked()){
                   level="extreme";
               }
               Intent prevIntent=getIntent();
               User user=(User) prevIntent.getSerializableExtra("user");
                assert user != null;
                user.setActivity_level(level);
                Intent intent = new Intent(ActivityLevel.this, Goal.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, "Selected activity level: " + message, Toast.LENGTH_SHORT).show();
    }
}