package com.qwertify.project.data.network.model.login;

import com.google.gson.annotations.SerializedName;

public class ErrorLoginNetworkApi {

    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }
}
