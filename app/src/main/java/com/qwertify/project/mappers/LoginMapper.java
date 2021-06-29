package com.qwertify.project.mappers;

import com.qwertify.project.data.network.model.login.LoginNetworkApi;
import com.qwertify.project.domain.model.login.UserLogin;

public class LoginMapper implements EntityMapper<LoginNetworkApi, UserLogin> {

    @Override
    public UserLogin mapFromEntity(LoginNetworkApi loginNetworkApi) {
        return new UserLogin(
                loginNetworkApi.getJwt(),
                loginNetworkApi.getUser().getEmail(),
                loginNetworkApi.getUser().getName(),
                loginNetworkApi.getUser().getEmailVerifiedAt());
    }

    @Override
    public LoginNetworkApi mapToEntity(UserLogin userLogin) {
        return null;
    }
}
