package com.qwertify.project.data.network.model.register;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterIfAccountExistsNetworkEntity {

	@SerializedName("email")
	private List<String> email;

	public List<String> getEmail() {
		return email;
	}
}