package com.zubisofts.payoneerex.ui.products;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;

import com.jraska.livedata.TestObserver;
import com.zubisofts.payoneerex.model.Product;
import com.zubisofts.payoneerex.utils.ProductsContract;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class ProductsViewModelTest {

    ProductsViewModel productsViewModel = new ProductsViewModel();
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void addProductShouldReturnTheCorrectValue() throws InterruptedException {
        productsViewModel.addProduct(ProductsContract.fetchFakeDbProducts().get(0));
        LiveData<List<Product>> productsLiveData = productsViewModel.getProducts();
        TestObserver.test(productsLiveData)
                .awaitValue()
                .assertHasValue()
                .assertValue(value -> value.size() == 1)
                .assertValue(value -> value.get(0).getId() == 1);
    }

}
