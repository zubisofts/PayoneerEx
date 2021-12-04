package com.zubisofts.payoneerex.ui.paymentdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zubisofts.payoneerex.repository.PaymentRepository;
import com.zubisofts.payoneerex.utils.State;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PaymentDetailsViewModel extends ViewModel {
    public PaymentRepository repository;
    @Inject
    PaymentDetailsViewModel(PaymentRepository repository){
        this.repository = repository;
    }

    public LiveData<State<?>> validatePaymentDetails(String url, String details){
        return repository.validatePaymentDetails(url, details);
    }


}