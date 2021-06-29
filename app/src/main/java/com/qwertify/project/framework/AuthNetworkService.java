package com.qwertify.project.framework;

import com.qwertify.project.utils.Constants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthNetworkService {

    private static Retrofit instance;

    private AuthNetworkService() {
    }

    public static Retrofit getAuthNetworkService() {

        if (instance == null) {
            //define client
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", Constants.TOKEN)
                            .build();
                    return chain.proceed(newRequest);
                }
            }).build();

            instance = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(Constants.BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                            new OkHttpClient.Builder()
                                    .addInterceptor(new HttpLoggingInterceptor()
                                            .setLevel(HttpLoggingInterceptor.Level.BASIC))
                                    .build()
                    )
                    .build();
        }
        return instance;
    }


}
