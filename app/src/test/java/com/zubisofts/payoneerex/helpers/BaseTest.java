package com.zubisofts.payoneerex.helpers;

import com.zubisofts.payoneerex.network.HttpClient;
import com.zubisofts.payoneerex.network.LoggingInterceptor;
import com.zubisofts.payoneerex.network.PaymentService;

import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseTest {

    public PaymentService paymentsService;
    private MockWebServer mockWebServer;

    @Before
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.setDispatcher(new PaymentDispatcher());
        mockWebServer.start(8080);
        HttpLoggingInterceptor loggingInterceptor = LoggingInterceptor.create();
        OkHttpClient okHttpClient = HttpClient.create(loggingInterceptor);

        paymentsService = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("http://127.0.0.1:8080/"))
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PaymentService.class);
    }

    @After
    public void teardown() throws IOException {
        mockWebServer.shutdown();
    }
}
