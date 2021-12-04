package com.zubisofts.payoneerex.utils;

import com.zubisofts.payoneerex.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsContract {

    public static ArrayList<Product> fetchFakeDbProducts(){
        ArrayList<Product> products = new ArrayList<>();
        for (int i= 0; i<15; i++){
            products.add(new Product(i+1, "Product Item "+(i+1), (i+1) * 100));
        }

        return products;
    }
}
