package com.example.stepcal.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.stepcal.API.ApiInterface;
import com.example.stepcal.DTO.User;
import com.example.stepcal.R;
import com.example.stepcal.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Leaderboard extends AppCompatActivity {

    private ApiInterface apiInterface;
    private String authToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leaderboard);

        authToken = getSavedToken();
        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);

        fetchLeaderboard();
    }

    private void fetchLeaderboard() {
        apiInterface.getLeaderBoard("Bearer " + authToken).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> leaderboard = response.body();
                if (leaderboard != null) {
                    populateLeaderboard(leaderboard);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void populateLeaderboard(List<User> leaderboard) {
        TableLayout tableLayout = findViewById(R.id.leaderboard_table);
        int c=1;
        for (User user : leaderboard) {
            TableRow row = new TableRow(this);

            // Rank
            TextView rankTextView = new TextView(this);
            rankTextView.setText(String.valueOf(c));
            rankTextView.setTextSize(22);
            rankTextView.setPadding(8, 8, 8, 8);
            row.addView(rankTextView);

            // User
            TextView userTextView = new TextView(this);
            userTextView.setText(user.getFirst_name());
            userTextView.setTextSize(22);
            userTextView.setPadding(8, 8, 8, 8);
            row.addView(userTextView);

            // Score
            TextView scoreTextView = new TextView(this);
            scoreTextView.setText(String.valueOf(user.getPoint()));
            scoreTextView.setTextSize(22);
            scoreTextView.setPadding(8, 8, 8, 8);
            row.addView(scoreTextView);

            tableLayout.addView(row);
            c++;
        }
    }

    private String getSavedToken() {
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getString("token", "");
    }
}
