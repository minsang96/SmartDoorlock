package com.team.SmartDoorlock.network;

import com.team.SmartDoorlock.data.JoinData;
import com.team.SmartDoorlock.data.JoinResponse;
import com.team.SmartDoorlock.data.LoginData;
import com.team.SmartDoorlock.data.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceApi {
    @POST("/user/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @POST("/user/join")
    Call<JoinResponse> userJoin(@Body JoinData data);
}