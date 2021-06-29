package com.qwertify.project.data.network.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginNetworkApi {

	@SerializedName("jwt")
	private String jwt;

	@SerializedName("user")
	private UserNetworkEntity userNetworkEntity;

	public String getJwt() {
		return jwt;
	}

	public UserNetworkEntity getUser() {
		return userNetworkEntity;
	}
}