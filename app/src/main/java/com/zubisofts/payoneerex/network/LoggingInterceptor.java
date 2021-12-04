package com.zubisofts.payoneerex.network;

import com.zubisofts.payoneerex.BuildConfig;

import okhttp3.logging.HttpLoggingInterceptor;

public class LoggingInterceptor {
    public static HttpLoggingInterceptor create() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return interceptor;
    }
}