package com.zubisofts.payoneerex.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

import retrofit2.HttpException;
import timber.log.Timber;

public class ErrorStateUtil {

    public static <T> State<T> decodeErrorState(@Nullable T data){
        State<T> error;
        if (data instanceof HttpException) {
            error = parseHttpException(data);
        } else if (data instanceof IOException) {
            Timber.e(((IOException) data));
            error = new State<>(Status.N0_CONNECTION, data);
        } else {
            Timber.e("Unknown error %s", data.toString());
            error = new State<>(Status.ERROR, data);
        }
        return error;
    }

    @NonNull
    private static <T> State<T> parseHttpException(@NonNull T data) {
        State<T> error;
        HttpException exception = (HttpException) data;
        switch (exception.code()) {
            case 404:
                error = new State<>(Status.NOT_FOUND, data);
                break;
            case 500:
                error = new State<>(Status.SERVER_ERROR, data);
                break;
            default:
                Timber.e(exception);
                error = new State<>(Status.UNKNOWN_CODE, data);
                break;
        }
        return error;
    }
}
