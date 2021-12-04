package com.zubisofts.payoneerex.ui.paymentnetworks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.zubisofts.payoneerex.R;
import com.zubisofts.payoneerex.databinding.FragmentPaymentNetworksBinding;
import com.zubisofts.payoneerex.model.Applicable;
import com.zubisofts.payoneerex.utils.Status;
import com.zubisofts.payoneerex.viewmodel.PaymentViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentNetworkSelectionFragment extends Fragment implements PaymentMethodListAdaptor.PaymentMethodSelectionListener {

    private FragmentPaymentNetworksBinding binding;
    private PaymentMethodListAdaptor adaptor;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentPaymentNetworksBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PaymentViewModel paymentViewModel = new ViewModelProvider(this).get(PaymentViewModel.class);

        adaptor = new PaymentMethodListAdaptor();
        adaptor.setPaymentMethodSelectionListener(this);
        binding.paymentList.setAdapter(adaptor);

        fetchPaymentNetworks(paymentViewModel);

        binding.btnRetry.setOnClickListener(view1 -> fetchPaymentNetworks(paymentViewModel));

    }

    private void fetchPaymentNetworks(PaymentViewModel paymentViewModel) {
        paymentViewModel.getPaymentNetworks().observe(getViewLifecycleOwner(), state -> {
            if (state.status == Status.LOADING) {
                displayLoader();
            } else if (state.status == Status.ERROR) {
                displayError(getString(R.string.error_text));
            } else if(state.status == Status.SUCCESS){
                List<Applicable> paymentNetworks = (List<Applicable>) state.data;
                displayResult(paymentNetworks);
            }else if(state.status == Status.N0_CONNECTION){
                displayError(getString(R.string.connection_error_text));
            }else if(state.status == Status.NOT_FOUND){
                displayError(getString(R.string.not_found_text));
            }else if(state.status == Status.UNKNOWN_CODE){
                displayError("Sorry, unknown error occurred!");
            }
        });
    }

    private void displayResult(List<Applicable> paymentNetworks) {
        binding.progressIndicator.setVisibility(View.GONE);
        binding.paymentList.setVisibility(View.VISIBLE);
        binding.errorLayout.setVisibility(View.GONE);
        adaptor.setPaymentMethods(paymentNetworks);
    }

    private void displayError(String error) {
        binding.progressIndicator.setVisibility(View.GONE);
        binding.errorLayout.setVisibility(View.VISIBLE);
        binding.paymentList.setVisibility(View.GONE);
        binding.errorText.setText(error);
//        Toast.makeText(getContext(), "An error occurred", Toast.LENGTH_SHORT).show();
    }

    private void displayLoader() {
        binding.progressIndicator.setVisibility(View.VISIBLE);
        binding.paymentList.setVisibility(View.GONE);
        binding.errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onPaymentMethodSelected(Applicable applicable) {
        PaymentNetworkSelectionFragmentDirections.ActionPaymentNetworksFragmentToPaymentFragment action =
                PaymentNetworkSelectionFragmentDirections.actionPaymentNetworksFragmentToPaymentFragment(applicable);
        NavHostFragment.findNavController(PaymentNetworkSelectionFragment.this)
                .navigate(action);
    }
}