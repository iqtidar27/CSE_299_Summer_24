package com.example.stepcal.API;



import com.example.stepcal.DTO.CompletedTask;
import com.example.stepcal.DTO.JwtResponse;
import com.example.stepcal.DTO.SignInRequest;
import com.example.stepcal.DTO.Task;
import com.example.stepcal.DTO.User;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {
    @POST("/auth/save")
    Call<User> saveUser(@Body User user);

    @POST("/auth/login")
    Call<JwtResponse>login(@Body SignInRequest signInRequest);

    @GET("/user/profile")
    Call<User> getProfile(@Header("Authorization") String token);

    @GET("/user/task")
    Call<Task> getTask(@Header("Authorization") String token);

    @POST("/user/add-task")
    Call<ResponseBody>addTask(@Header("Authorization")String token, @Body CompletedTask task);

    @GET("/user/task-history")
    Call<List<CompletedTask>>taskHistory(@Header("Authorization")String token);

    @PUT("/user/update-point")
    Call<ResponseBody>updatePoint(@Header("Authorization")String token);

    @GET("/user/leaderboard")
    Call<List<User>>getLeaderBoard(@Header("Authorization")String token);

}
