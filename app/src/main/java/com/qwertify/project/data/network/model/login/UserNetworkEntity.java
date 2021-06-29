package com.qwertify.project.data.network.model.login;

import com.google.gson.annotations.SerializedName;

public class UserNetworkEntity {

	@SerializedName("emailVerifiedAt")
	private String emailVerifiedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("fbId")
	private String fbId;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	public String getEmailVerifiedAt() {
		return emailVerifiedAt;
	}

	public String getName() {
		return name;
	}

	public String getFbId() {
		return fbId;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
}