package com.example.stepcal.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stepcal.API.ApiInterface;
import com.example.stepcal.DTO.User;
import com.example.stepcal.R;
import com.example.stepcal.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {

    private ApiInterface apiInterface;

    private String userEmail;
    private String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        apiInterface= RetrofitClient.getRetrofit().create(ApiInterface.class);
        authToken=getSavedToken();

        Button task=findViewById(R.id.task);
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Profile.this,Tasks.class);
                startActivity(intent);
            }
        });
      Button leaderboard_button= findViewById(R.id.leaderboard_button);
      leaderboard_button.setOnClickListener(new View.OnClickListener(){
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(Profile.this, Leaderboard.class);
              startActivity(intent);
          }
      });

        apiInterface.getProfile("Bearer "+authToken).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if(response.isSuccessful()) {
                    User profile = response.body();
                    if (profile != null) {
                        // Setting Name
                        TextView nameTextView = findViewById(R.id.name);
                        String fullName = profile.getFirst_name() + " " + profile.getLast_name();
                        nameTextView.setText(fullName);

                        // Setting Phone Number
                        TextView phoneTextView = findViewById(R.id.phone);
                        phoneTextView.setText(String.valueOf(profile.getPhone_no()));

                        // Setting Age
                        TextView ageTextView = findViewById(R.id.age);
                        ageTextView.setText(String.valueOf(profile.getAge()));

                        //srtting email
                        TextView emailTextview = findViewById(R.id.email);
                        emailTextview.setText(String.valueOf(profile.getEmail()));

                        // Setting Gender
                        TextView genderTextView = findViewById(R.id.gender);
                        genderTextView.setText(profile.getGender());

                        // Setting Goal
                        TextView goalTextView = findViewById(R.id.goal);
                        goalTextView.setText(profile.getGoal());
                    }
                } else {
                    Toast.makeText(Profile.this, "Failed to fetch profile", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(Profile.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getSavedToken() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getString("token", "");
    }
}
