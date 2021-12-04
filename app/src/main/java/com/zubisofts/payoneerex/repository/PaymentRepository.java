package com.zubisofts.payoneerex.repository;

import androidx.lifecycle.LiveData;

import com.zubisofts.payoneerex.utils.State;

public interface PaymentRepository {

    LiveData<State<?>> fetchPaymentMethods();
    LiveData<State<?>> validatePaymentDetails(String url, String details);

}
