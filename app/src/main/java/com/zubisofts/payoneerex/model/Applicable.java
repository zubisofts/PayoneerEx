
package com.zubisofts.payoneerex.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Applicable implements Serializable {

    @SerializedName("code")
    private String code;

    @SerializedName("label")
    private String label;

    @SerializedName("method")
    private String method;

    @SerializedName("grouping")
    private String grouping;

    @SerializedName("registration")
    private String registration;

    @SerializedName("recurrence")
    private String recurrence;

    @SerializedName("redirect")
    private Boolean redirect;

    @SerializedName("links")
    private Links1 links;

    @SerializedName("selected")

    private Boolean selected;
    @SerializedName("inputElements")

    private List<InputElement> inputElements = null;
    @SerializedName("operationType")

    private String operationType;
    @SerializedName("contractData")

    private ContractData contractData;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getGrouping() {
        return grouping;
    }

    public void setGrouping(String grouping) {
        this.grouping = grouping;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(String recurrence) {
        this.recurrence = recurrence;
    }

    public Boolean getRedirect() {
        return redirect;
    }

    public void setRedirect(Boolean redirect) {
        this.redirect = redirect;
    }

    public Links1 getLinks() {
        return links;
    }

    public void setLinks(Links1 links) {
        this.links = links;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public List<InputElement> getInputElements() {
        return inputElements;
    }

    public void setInputElements(List<InputElement> inputElements) {
        this.inputElements = inputElements;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ContractData getContractData() {
        return contractData;
    }

    public void setContractData(ContractData contractData) {
        this.contractData = contractData;
    }

}
