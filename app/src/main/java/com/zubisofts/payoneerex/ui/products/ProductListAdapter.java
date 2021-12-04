package com.zubisofts.payoneerex.ui.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.zubisofts.payoneerex.R;
import com.zubisofts.payoneerex.databinding.ProductItemBinding;
import com.zubisofts.payoneerex.model.Product;

import java.util.ArrayList;

public class ProductListAdapter extends ListAdapter<Product, ProductListAdapter.ProductItemHolder> {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Product> checkedProducts = new ArrayList<>();
    private ProductItemClickListener clickListener;

    protected ProductListAdapter() {
        super(new DiffUtil.ItemCallback<Product>() {
            @Override
            public boolean areItemsTheSame(
                    @NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(
                    @NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ProductItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         ProductItemBinding binding = ProductItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
         return new ProductItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product,checkedProducts, clickListener);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setClickListener(ProductItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyItemRangeInserted(0, products.size());
    }

    static class ProductItemHolder extends RecyclerView.ViewHolder{

        final ProductItemBinding productItemBinding;

        public ProductItemHolder(@NonNull ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }

        public void bind(Product product,ArrayList<Product> checkedProducts, ProductItemClickListener clickListener){
            productItemBinding.txtProductName.setText(product.getName());
            productItemBinding.txtProductPrice.setText(String.valueOf(product.getPrice()));
            productItemBinding.getRoot().setChecked(checkedProducts.contains(product));
            productItemBinding.getRoot().setOnClickListener(view -> {
                if(productItemBinding.getRoot().isChecked()){
                    productItemBinding.getRoot().setChecked(false);
                    checkedProducts.remove(product);
                    clickListener.onRemoveProduct(product);
                }else {
                    productItemBinding.getRoot().setChecked(true);
                    checkedProducts.add(product);
                    clickListener.onAddProduct(product);
                }
//                clickListener.onProductItemSelected(product);
            });
        }
    }

    interface ProductItemClickListener{
        void onAddProduct(Product product);
        void onRemoveProduct(Product product);
    }
}
