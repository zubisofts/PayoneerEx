package com.zubisofts.payoneerex.ui.paymentdetails;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.zubisofts.payoneerex.R;
import com.zubisofts.payoneerex.databinding.PaymentFragmentBinding;
import com.zubisofts.payoneerex.databinding.TextInputLayoutBinding;
import com.zubisofts.payoneerex.model.Applicable;
import com.zubisofts.payoneerex.model.InputElement;
import com.zubisofts.payoneerex.model.validation.ValidationResponse;
import com.zubisofts.payoneerex.utils.Status;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentFragment extends Fragment {

    private PaymentDetailsViewModel mViewModel;
    private PaymentFragmentBinding binding;
    private HashMap<String, EditText> details = new HashMap<>();
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = PaymentFragmentBinding.bind(inflater.inflate(R.layout.payment_fragment, container, false));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PaymentDetailsViewModel.class);

        progressDialog = new ProgressDialog(getContext());
        displayInputFields();

        binding.btnPayNow.setOnClickListener(v->{
            tryPostValidation();
        });

    }

    private void tryPostValidation() {
        Applicable method = PaymentFragmentArgs.fromBundle(getArguments()).getPaymentMethod();
        String detailsJson = formatDetialsJson();
        mViewModel.validatePaymentDetails(method.getLinks().getValidation(), detailsJson)
                .observe(getViewLifecycleOwner(), validationResponse->{
                    Status status = validationResponse.status;
                    if(status == Status.LOADING){
                        showLoadingDialog();
                    }else if (status == Status.ERROR){
                        progressDialog.dismiss();
                        Toast.makeText(requireContext(), "Unknown error occurred", Toast.LENGTH_SHORT).show();
                    }else if (status == Status.SUCCESS){
                        ValidationResponse response = (ValidationResponse) validationResponse.data;
                        if(response.getValid()){
                            showSuccessDialog();
                        }else{
                            showErrorDialog(response.getMessages().toString());
                        }
                    }
                });
    }

    private void showErrorDialog(String error) {
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }

        new AlertDialog.Builder(requireContext())
                .setMessage(error)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }).create().show();
    }

    private void showSuccessDialog() {
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        new AlertDialog.Builder(requireContext())
                .setMessage("Validation was successful")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                }).create().show();
    }

    private void showLoadingDialog() {
        progressDialog.setMessage("Validating payment details");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    private String formatDetialsJson() {
        JSONObject jsonObject = new JSONObject();
        ArrayList<String> keys = new ArrayList<>(details.keySet());
        ArrayList<EditText> editTexts = new ArrayList<>(details.values());

        for(int i=0; i<details.size(); i++){
            try {
                jsonObject.putOpt(keys.get(i), editTexts.get(i).getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }

    private void displayInputFields() {
        details.clear();
        Applicable method = PaymentFragmentArgs.fromBundle(getArguments()).getPaymentMethod();
        LinearLayout detailsLayout = binding.detailsLayout;
        if(method.getInputElements() != null)
        for (InputElement inputElement: method.getInputElements()) {
            TextInputLayoutBinding textInputLayoutBinding = TextInputLayoutBinding.inflate(LayoutInflater.from(requireContext()));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 16, 0, 16);
            TextInputLayout inputLayout = textInputLayoutBinding.inputLayout;
            inputLayout.setLayoutParams(lp);
            setInputType(inputElement.getType(), inputLayout.getEditText());
            inputLayout.setHint(inputElement.getName());

            details.put(inputElement.getName(), inputLayout.getEditText());
            detailsLayout.addView(textInputLayoutBinding.getRoot());
        }
    }

    private void setInputType(String type, EditText editText) {
        if(type.equals("numeric") || type.equals("number")){
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }else{
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }
}