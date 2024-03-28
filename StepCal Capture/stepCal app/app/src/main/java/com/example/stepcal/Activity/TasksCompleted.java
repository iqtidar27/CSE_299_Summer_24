package com.example.stepcal.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.example.stepcal.API.ApiInterface;
import com.example.stepcal.DTO.CompletedTask;
import com.example.stepcal.R;
import com.example.stepcal.Retrofit.RetrofitClient;
import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stepcal.DTO.CompletedTask;
import com.google.gson.Gson;

import java.util.List;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.stepcal.DTO.CompletedTask;
import com.google.gson.Gson;

import java.util.List;

public class TasksCompleted extends AppCompatActivity {

    private ApiInterface apiInterface;

    private String userEmail;
    private String authToken;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_completed);
        apiInterface= RetrofitClient.getRetrofit().create(ApiInterface.class);
        authToken=getSavedToken();
        Intent prevIntent=getIntent();
        CompletedTask completedTask= (CompletedTask) prevIntent.getSerializableExtra("complete");

        tableLayout = findViewById(R.id.taskList); // assuming this is the ID of your TableLayout

        apiInterface.addTask("Bearer "+authToken,completedTask).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                // Handle response
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                // Handle failure
            }
        });

        apiInterface.updatePoint("Bearer "+authToken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                assert response.body() != null;
                Toast.makeText(TasksCompleted.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        apiInterface.taskHistory("Bearer "+authToken).enqueue(new Callback<List<CompletedTask>>() {
            @Override
            public void onResponse(Call<List<CompletedTask>> call2, Response<List<CompletedTask>> response2) {
                List<CompletedTask> progress = response2.body();
                assert progress != null;
                addTableRow("Email", "Calorie Burn", "Calorie Intake", "Date", true);
                for(CompletedTask task : progress){
                    addTableRow(task.getEmail(), String.valueOf(task.getCalorie_burn()), String.valueOf(task.getCalorie_intake()), task.getDate(), false);
                }
            }

            @Override
            public void onFailure(Call<List<CompletedTask>> call2, Throwable t2) {
                // Handle failure
            }
        });
    }

    private void addTableRow(String email, String calorieBurn, String calorieIntake, String date, boolean isHeader) {
        TableRow row = new TableRow(this);

        TextView emailTextView = createTextView(email, isHeader);
        TextView calorieBurnTextView = createTextView(calorieBurn, isHeader);
        TextView calorieIntakeTextView = createTextView(calorieIntake, isHeader);
        TextView dateTextView = createTextView(date, isHeader);

        row.addView(emailTextView);
        row.addView(calorieBurnTextView);
        row.addView(calorieIntakeTextView);
        row.addView(dateTextView);

        tableLayout.addView(row);
    }

    private TextView createTextView(String text, boolean isHeader) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(10, 10, 10, 10);
        textView.setGravity(Gravity.CENTER);
        if (isHeader) {
            textView.setTextSize(20); // Increase text size for header
            textView.setTextColor(getResources().getColor(android.R.color.black)); // Set color for header text
        } else {
            textView.setTextSize(16); // Set text size for row data
        }
        return textView;
    }
    private String getSavedToken() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getString("token", "");
    }
}
