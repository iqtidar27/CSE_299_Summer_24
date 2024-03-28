package com.example.stepcal.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stepcal.API.ApiInterface;
import com.example.stepcal.DTO.User;
import com.example.stepcal.MainActivity;
import com.example.stepcal.R;
import com.example.stepcal.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button submit=findViewById(R.id.submit);
        System.out.println("in");
        //submit.setBackgroundColor(Color.BLUE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doSave();
            }
        });
    }

    public void doSave() {

        
        EditText first_name=findViewById(R.id.first_name);
        EditText last_name=findViewById(R.id.last_name);
        EditText email=findViewById(R.id.email);
        EditText phone=findViewById(R.id.phone_no);
        EditText gender=findViewById(R.id.gender);
        EditText password=findViewById(R.id.password);
        EditText height=findViewById(R.id.height);
        EditText weight=findViewById(R.id.weight);
        EditText age=findViewById(R.id.age);
        //System.out.println(first_name.toString());
        User user = new User();
        user.setFirst_name(first_name.getText().toString());
        user.setLast_name(last_name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setGender(gender.getText().toString());
        user.setAge(Integer.parseInt(age.getText().toString()));
        user.setHeight(Double.parseDouble(height.getText().toString()));
        user.setPhone_no(Long.parseLong(phone.getText().toString()));
        user.setWeight(Double.parseDouble(weight.getText().toString()));
        Intent intent=new Intent(this,ActivityLevel.class);
        intent.putExtra("user",user);
        startActivity(intent);
    }
}