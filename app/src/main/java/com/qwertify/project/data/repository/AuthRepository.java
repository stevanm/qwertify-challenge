package com.qwertify.project.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.qwertify.project.data.network.Resource;
import com.qwertify.project.domain.model.login.UserLogin;

public interface AuthRepository {

    void login(String email, String password);

    void cacheAuthData(String token);

    String getCachedAuthData();

    MutableLiveData<UserLogin> observableUserLoginData();

    MutableLiveData<Resource> observableUserLoginStatus();
}
