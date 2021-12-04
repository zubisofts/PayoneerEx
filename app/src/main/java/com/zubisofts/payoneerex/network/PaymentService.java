package com.zubisofts.payoneerex.network;

import com.zubisofts.payoneerex.model.PayMethodsResponse;
import com.zubisofts.payoneerex.model.validation.ValidationResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface PaymentService {

    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Call<PayMethodsResponse> getPaymentMethods();

    @POST
    Call<ValidationResponse> validatePaymentDetails(@Url String validationUrl, @Body String payDetailsJson);
}
