package com.qwertify.project.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.qwertify.project.data.network.Resource;
import com.qwertify.project.data.network.model.login.LoginNetworkApi;
import com.qwertify.project.domain.model.login.UserLogin;
import com.qwertify.project.framework.AuthApi;
import com.qwertify.project.framework.QwertifyApplication;
import com.qwertify.project.mappers.LoginMapper;
import com.qwertify.project.utils.Constants;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {

    private final QwertifyApplication application;
    private final LoginMapper loginMapper;

    //observable data
    private final MutableLiveData<UserLogin> userLoginMutableLiveData = new MutableLiveData<>();
    //status indicator
    private final MutableLiveData<Resource> resourceStatusLiveData = new MutableLiveData<>();

    public AuthRepositoryImpl(QwertifyApplication application, LoginMapper loginMapper) {
        this.application = application;
        this.loginMapper = loginMapper;
    }

    @Override
    public void login(String email, String password) {
        resourceStatusLiveData.setValue(new Resource.Loading());

        //setup params
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        Call<LoginNetworkApi> call = application.getAuthNetworkService().create(AuthApi.class).login(credentials);
        call.enqueue(new Callback<LoginNetworkApi>() {
            @Override
            public void onResponse(Call<LoginNetworkApi> call, Response<LoginNetworkApi> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getUser() != null) {
                        Log.d("Data:", response.body().toString());
                        userLoginMutableLiveData.postValue(loginMapper.mapFromEntity(response.body()));
                        resourceStatusLiveData.setValue(new Resource.Success());
                    }
                } else {
                    Log.d("Error:", response.message());
                    resourceStatusLiveData.setValue(new Resource.Error(response.message()));
                }
            }

            @Override
            public void onFailure(Call<LoginNetworkApi> call, Throwable t) {
                Log.d("Error:", t.getLocalizedMessage());
                resourceStatusLiveData.setValue(new Resource.Error(t.getLocalizedMessage()));
            }
        });

    }

    @Override
    public void cacheAuthData(String token) {
        SharedPreferences sharedPreferences = application.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(Constants.TOKEN, token).apply();
    }

    @Override
    public String getCachedAuthData() {
        SharedPreferences sharedPreferences = application.getSharedPreferences(Constants.SHARED_PREFS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Constants.TOKEN, null);
    }

    @Override
    public MutableLiveData<UserLogin> observableUserLoginData() {
        return userLoginMutableLiveData;
    }

    @Override
    public MutableLiveData<Resource> observableUserLoginStatus() {
        return resourceStatusLiveData;
    }
}
