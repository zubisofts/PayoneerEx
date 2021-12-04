package com.zubisofts.payoneerex.ui.products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.zubisofts.payoneerex.R;
import com.zubisofts.payoneerex.model.Product;
import com.zubisofts.payoneerex.utils.ProductsContract;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProductsFragment extends Fragment implements ProductListAdapter.ProductItemClickListener {

    private com.zubisofts.payoneerex.databinding.FragmentProductsBinding binding;
    private ProductsViewModel productsViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = com.zubisofts.payoneerex.databinding.FragmentProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        productsViewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        fillProductsList();
        observeProduct();
        binding.btnCheckout.setOnClickListener(view1 ->
                NavHostFragment.findNavController(ProductsFragment.this)
                .navigate(R.id.action_ProductsFragment_to_PayFragment));
    }

    private void observeProduct() {
        productsViewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            if(products.isEmpty()){
                binding.btnCheckout.setEnabled(false);
            }else{
                binding.btnCheckout.setEnabled(true);
            }
        });
    }

    private void fillProductsList() {
        ProductListAdapter productListAdapter = new ProductListAdapter();
        productListAdapter.setClickListener(this);
        productListAdapter.setProducts(ProductsContract.fetchFakeDbProducts());
        binding.productsRecyclerView.setAdapter(productListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAddProduct(Product product) {
        productsViewModel.addProduct(product);
    }

    @Override
    public void onRemoveProduct(Product product) {
        productsViewModel.removeProduct(product);
    }
}