package com.qwertify.project.presentation.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.qwertify.project.data.network.Resource;
import com.qwertify.project.data.repository.AuthRepository;
import com.qwertify.project.domain.model.login.UserLogin;
import com.qwertify.project.framework.QwertifyApplication;

import org.jetbrains.annotations.NotNull;

public class AuthViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;

    public AuthViewModel(@NonNull @NotNull Application application) {
        super(application);
        authRepository = ((QwertifyApplication) application).getAuthRepository();
    }

    public void login(String email, String password) {
        authRepository.login(email, password);
    }

    public void cacheAuthData(String token) {
        authRepository.cacheAuthData(token);
    }

    public String getCachedAuthData() {
        return authRepository.getCachedAuthData();
    }

    public MutableLiveData<UserLogin> observableUserLoginData() {
        return authRepository.observableUserLoginData();
    }

    public MutableLiveData<Resource> observableUserLoginStatus() {
        return authRepository.observableUserLoginStatus();
    }

}