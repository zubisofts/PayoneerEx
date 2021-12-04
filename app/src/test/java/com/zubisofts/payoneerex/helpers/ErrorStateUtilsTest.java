package com.zubisofts.payoneerex.helpers;

import com.google.common.truth.Truth;
import com.zubisofts.payoneerex.utils.ErrorStateUtil;
import com.zubisofts.payoneerex.utils.State;
import com.zubisofts.payoneerex.utils.Status;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;

public class ErrorStateUtilsTest {

    private ResponseBody fakeBody;

    @Before
    public void init() {
        fakeBody = Mockito.mock(ResponseBody.class);
    }

    @Test
    public void givenValidHttpExceptionDataReturnNotFoundStatus() {
        // Given an http exception
        HttpException exception = new HttpException(Response.error(404, fakeBody));

        // When the get error method is called
        State<Exception> exceptionRes = ErrorStateUtil.decodeErrorState(exception);

        // Confirm that the instance of the error is 404
        Truth.assertThat(exceptionRes.status).isEqualTo(Status.NOT_FOUND);
    }

    @Test
    public void givenValidHttpExceptionDataReturnServerErrorStatus() {
        // Given an http exception
        HttpException exception = new HttpException(Response.error(500, fakeBody));

        // When the get error method is called
        State<Exception> exceptionRes = ErrorStateUtil.decodeErrorState(exception);

        // Confirm that the instance of the error is 404
        Truth.assertThat(exceptionRes.status).isEqualTo(Status.SERVER_ERROR);
    }

    @Test
    public void givenValidIOExceptionDataReturnNoConnectionStatus() {
        // Given an IO exception
        IOException exception = new IOException();

        // When the get error method is called
        State<Exception> exceptionRes = ErrorStateUtil.decodeErrorState(exception);

        // Confirm that the instance of the error is 404
        Truth.assertThat(exceptionRes.status).isEqualTo(Status.N0_CONNECTION);
    }

    @Test
    public void givenValidHttpExceptionDataReturnUnknownCodeStatus() {
        // Given an http exception
        HttpException exception = new HttpException(Response.error(403, fakeBody));

        // When the get error method is called
        State<Exception> exceptionRes = ErrorStateUtil.decodeErrorState(exception);

        // Confirm that the instance of the error is 404
        Truth.assertThat(exceptionRes.status).isEqualTo(Status.UNKNOWN_CODE);
    }

    @Test
    public void givenValidExceptionDataReturnGenericErrorStatus() {
        // Given an exception
        Exception exception = new Exception();

        // When the get error method is called
        State<Exception> exceptionRes = ErrorStateUtil.decodeErrorState(exception);

        // Confirm that the instance of the error is 404
        Truth.assertThat(exceptionRes.status).isEqualTo(Status.ERROR);
    }
}