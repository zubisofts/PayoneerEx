package com.zubisofts.payoneerex.di;

import com.zubisofts.payoneerex.network.PaymentService;
import com.zubisofts.payoneerex.network.HttpClient;
import com.zubisofts.payoneerex.network.LoggingInterceptor;
import com.zubisofts.payoneerex.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return HttpClient.create(httpLoggingInterceptor);
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor() {
        return LoggingInterceptor.create();
    }

    @Singleton
    @Provides
    PaymentService provideApiRepo(Retrofit retrofit) {
        return retrofit.create(PaymentService.class);
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
