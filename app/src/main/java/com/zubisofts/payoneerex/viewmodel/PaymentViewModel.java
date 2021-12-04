package com.zubisofts.payoneerex.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zubisofts.payoneerex.model.Applicable;
import com.zubisofts.payoneerex.model.PayMethodsResponse;
import com.zubisofts.payoneerex.repository.PaymentRepository;
import com.zubisofts.payoneerex.utils.State;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PaymentViewModel extends ViewModel {
    public PaymentRepository repository;
    @Inject
    PaymentViewModel(PaymentRepository repository){
        this.repository = repository;
    }

    public LiveData<State<?>> getPaymentNetworks(){
        return repository.fetchPaymentMethods();
    }
}
