package com.zubisofts.payoneerex.ui.products;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zubisofts.payoneerex.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductsViewModel extends ViewModel {

    // Used hashset to remove duplicate entries
    final Set<Product> products = new HashSet<Product>();

    private final MutableLiveData<List<Product>> productMutableLiveData
            = new MutableLiveData<>(new ArrayList<>(products));

    @Inject
    ProductsViewModel(){}

    public void addProduct(Product product) {
        products.add(product);
        productMutableLiveData.setValue(new ArrayList<>(products));
    }

    public void removeProduct(Product product) {
        products.remove(product);
        productMutableLiveData.setValue(new ArrayList<>(products));
    }

    public MutableLiveData<List<Product>> getProducts(){
        return productMutableLiveData;
    }

}
