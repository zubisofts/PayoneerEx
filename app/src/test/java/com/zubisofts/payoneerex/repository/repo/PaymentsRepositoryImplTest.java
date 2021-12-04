package com.zubisofts.payoneerex.repository.repo;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.jraska.livedata.TestObserver;
import com.zubisofts.payoneerex.helpers.BaseTest;
import com.zubisofts.payoneerex.model.Applicable;
import com.zubisofts.payoneerex.repository.PaymentRepository;
import com.zubisofts.payoneerex.repository.PaymentRepositoryImpl;
import com.zubisofts.payoneerex.utils.State;
import com.zubisofts.payoneerex.utils.Status;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class PaymentsRepositoryImplTest extends BaseTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private PaymentRepository repository;

    @Override
    public void setUp() throws IOException {
        super.setUp();
        repository = new PaymentRepositoryImpl(paymentsService);
    }

    @Test
    public void whenGetPaymentsReturnValidData() throws InterruptedException {
        LiveData<State<?>> payments = repository.fetchPaymentMethods();
        TestObserver.test(payments)
                .awaitValue()
                .assertHasValue()
                .assertValue(resource -> resource.status.equals(Status.LOADING))
                .awaitNextValue()
                .assertHasValue()
                .assertValue(resource -> {
                    assert resource.data != null;
                    List<Applicable> networks = (List<Applicable>) resource.data;
                    return networks.size() == 3;
                })
                .assertValue(resource -> resource.status.equals(Status.SUCCESS))
                .assertValue(resource -> {
                    assert resource.data != null;
                    List<Applicable> networks = (List<Applicable>) resource.data;
                    Applicable item = networks.get(0);
                    boolean isCorrectCode = item.getCode().equals("AMEX");
                    boolean isCorrectLink = item.getLinks().getLogo().equals("https://raw.githubusercontent.com/optile/checkout-android/develop/checkout/src/main/assets/networklogos/amex.png");
                    return isCorrectCode && isCorrectLink;
                });
    }
}