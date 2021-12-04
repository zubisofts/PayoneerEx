package com.zubisofts.payoneerex.ui.paymentnetworks;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zubisofts.payoneerex.MainActivity;
import com.zubisofts.payoneerex.R;
import com.zubisofts.payoneerex.model.Applicable;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodListAdaptor extends RecyclerView.Adapter<PaymentMethodListAdaptor.PaymentMethodItemHolder>{

    private List<Applicable> paymentMethods = new ArrayList<>();
    private PaymentMethodSelectionListener paymentMethodSelectionListener;

    @NonNull
    @Override
    public PaymentMethodItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_method_item, parent,false);
        return new PaymentMethodItemHolder(itemHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodItemHolder holder, int position) {
        if(paymentMethods.get(position) != null){
            Applicable applicable = paymentMethods.get(position);
            holder.label.setText(applicable.getLabel());
            Glide.with(holder.logo.getContext())
                    .load(applicable.getLinks().getLogo())
                    .into(holder.logo);

            holder.itemView.setOnClickListener(view -> {
                paymentMethodSelectionListener.onPaymentMethodSelected(applicable);
            });
        }
    }

    @Override
    public int getItemCount() {
        return paymentMethods.size();
    }

    public void setPaymentMethodSelectionListener(PaymentMethodSelectionListener paymentMethodSelectionListener) {
        this.paymentMethodSelectionListener = paymentMethodSelectionListener;
    }

    public void setPaymentMethods(List<Applicable> paymentMethods) {
        this.paymentMethods = paymentMethods;
        notifyItemRangeInserted(0, paymentMethods.size());
    }

    static class PaymentMethodItemHolder extends RecyclerView.ViewHolder{

        private ImageView logo;
        private TextView label;

        public PaymentMethodItemHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logo);
            label = itemView.findViewById(R.id.label);
        }
    }

    interface PaymentMethodSelectionListener{
        void onPaymentMethodSelected(Applicable applicable);
    }
}
