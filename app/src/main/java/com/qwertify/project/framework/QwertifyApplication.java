package com.qwertify.project.framework;

import android.app.Application;

import com.qwertify.project.data.repository.AuthRepository;
import com.qwertify.project.data.repository.AuthRepositoryImpl;
import com.qwertify.project.mappers.LoginMapper;

import retrofit2.Retrofit;

public class QwertifyApplication extends Application {

    private LoginMapper loginMapper;
    private AuthRepository authRepository;
    private Retrofit authNetworkService;

    @Override
    public void onCreate() {
        super.onCreate();

        //mappers
        loginMapper = new LoginMapper();

        //repositories
        authRepository = new AuthRepositoryImpl(this, loginMapper);

        //auth network service
        authNetworkService = AuthNetworkService.getAuthNetworkService();

    }

    public AuthRepository getAuthRepository() {
        return authRepository;
    }

    public Retrofit getAuthNetworkService() {
        return authNetworkService;
    }

}
