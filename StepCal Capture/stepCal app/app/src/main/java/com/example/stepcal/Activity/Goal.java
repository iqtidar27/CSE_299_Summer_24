package com.example.stepcal.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stepcal.API.ApiInterface;
import com.example.stepcal.DTO.User;
import com.example.stepcal.MainActivity;
import com.example.stepcal.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Goal extends AppCompatActivity {

    private ApiInterface apiInterface;
    private RadioGroup goalRadioGroup;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        goalRadioGroup = findViewById(R.id.goalRadioGroup);
        submitButton = findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface= com.example.stepcal.Retrofit.RetrofitClient.getRetrofit().create(ApiInterface.class);

                RadioButton gain = findViewById(R.id.gain);
                RadioButton loss = findViewById(R.id.loss);
                RadioButton build = findViewById(R.id.build);
                RadioButton maintain = findViewById(R.id.maintain);
                String goal="";
                if(gain.isChecked()){
                    goal="gain";
                }
                else if(loss.isChecked()){
                    goal="loss";
                }
                else if(build.isChecked()){
                    goal="build";
                }
                else if(maintain.isChecked()){
                    goal="maintain";
                }
                Intent prevIntent=getIntent();
                User user=(User) prevIntent.getSerializableExtra("user");
                System.out.println(user);
                assert user != null;
                user.setGoal(goal);

                apiInterface.saveUser(user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(Goal.this, "successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Goal.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(Goal.this, t.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
