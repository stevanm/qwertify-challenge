package com.qwertify.project.framework;

import com.qwertify.project.data.network.model.login.LoginNetworkApi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("api/login")
    Call<LoginNetworkApi> login(@Body HashMap<String, String> credentials);

}
