package com.example.stepcal.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stepcal.API.ApiInterface;
import com.example.stepcal.DTO.JwtResponse;
import com.example.stepcal.DTO.SignInRequest;
import com.example.stepcal.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doLogIn();
            }
        });
    }
    public void doLogIn(){
        apiInterface= com.example.stepcal.Retrofit.RetrofitClient.getRetrofit().create(ApiInterface.class);

        EditText email=findViewById(R.id.email);
        EditText password=findViewById(R.id.password);

        SignInRequest signInRequest=new SignInRequest();
        signInRequest.setEmail(email.getText().toString());
        signInRequest.setPassword(password.getText().toString());

        apiInterface.login(signInRequest).enqueue(new Callback<JwtResponse>() {
            @Override
            public void onResponse(@NonNull Call<JwtResponse> call, @NonNull Response<JwtResponse> response) {
                JwtResponse response1=response.body();

                assert response1 != null;
                saveCredentials(email.getText().toString(),response1.getToken());
                Intent intent=new Intent(Login.this,Profile.class);
                Toast.makeText(Login.this, "Successful", Toast.LENGTH_LONG).show();
                startActivity(intent);

            }

            @Override
            public void onFailure(@NonNull Call<JwtResponse> call, @NonNull Throwable t) {
                // Handle network errors here
                Toast.makeText(Login.this, "Network error: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void saveCredentials(String email,String token){
        SharedPreferences preferences=getSharedPreferences("MyPrefs",MODE_PRIVATE);

        SharedPreferences.Editor editor=preferences.edit();

        editor.putString("email",email);
        editor.putString("token",token);

        editor.apply();
    }
}