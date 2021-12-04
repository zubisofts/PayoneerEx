package com.zubisofts.payoneerex.di;

import com.zubisofts.payoneerex.network.PaymentService;
import com.zubisofts.payoneerex.repository.PaymentRepository;
import com.zubisofts.payoneerex.repository.PaymentRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepoModule {

    @Singleton
    @Provides
    public PaymentRepository providesPaymentsRepo(PaymentService paymentService) {
        return new PaymentRepositoryImpl(paymentService);
    }
}
