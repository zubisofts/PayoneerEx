package com.zubisofts.payoneerex.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.zubisofts.payoneerex.model.Applicable;
import com.zubisofts.payoneerex.model.PayMethodsResponse;
import com.zubisofts.payoneerex.model.validation.ValidationResponse;
import com.zubisofts.payoneerex.network.PaymentService;
import com.zubisofts.payoneerex.utils.State;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentRepositoryImpl implements PaymentRepository {

    PaymentService paymentService;

    MediatorLiveData<State<?>> paymentMethodsFetchData = new MediatorLiveData<>();
    MediatorLiveData<State<?>> paymentDetailsValidation = new MediatorLiveData<>();

    @Inject
    public PaymentRepositoryImpl(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @Override
    public LiveData<State<?>> fetchPaymentMethods() {
        paymentMethodsFetchData.setValue(State.loading());

        paymentService.getPaymentMethods().enqueue(new Callback<PayMethodsResponse>() {
            @Override
            public void onResponse(Call<PayMethodsResponse> call, Response<PayMethodsResponse> response) {
                int code = response.code();
                if(code==200){
                    assert response.body() != null;
                    List<Applicable> methods = response.body().getNetworks().getApplicable();
                    paymentMethodsFetchData.postValue(State.success(methods));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PayMethodsResponse> call, @NonNull Throwable t) {
                paymentMethodsFetchData.postValue(State.error(t));
            }
        });

        return paymentMethodsFetchData;
    }

    @Override
    public LiveData<State<?>> validatePaymentDetails(String url, String details) {
        paymentDetailsValidation.setValue(State.loading());

        paymentService.validatePaymentDetails(url, details).enqueue(new Callback<ValidationResponse>() {
            @Override
            public void onResponse(Call<ValidationResponse> call, Response<ValidationResponse> response) {
                int code = response.code();
                if(code==200){
                    assert response.body() != null;
                    ValidationResponse validationResponse = response.body();
                    paymentMethodsFetchData.postValue(State.success(validationResponse));
                }else{
                    paymentDetailsValidation.postValue(State.error(code));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ValidationResponse> call, @NonNull Throwable t) {
                paymentMethodsFetchData.postValue(State.error(t));
            }
        });

        return paymentDetailsValidation;
    }
}