
package com.zubisofts.payoneerex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PayMethodsResponse implements Serializable {

    @SerializedName("links")
    private Links links;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("operation")
    private String operation;

    @SerializedName("resultCode")
    private String resultCode;

    @SerializedName("resultInfo")
    private String resultInfo;

    @SerializedName("returnCode")
    private ReturnCode returnCode;

    @SerializedName("status")
    private Status status;

    @SerializedName("interaction")
    private Interaction interaction;

    @SerializedName("identification")
    private Identification identification;

    @SerializedName("networks")
    private Networks networks;

    @SerializedName("operationType")
    private String operationType;

    @SerializedName("style")
    private Style style;

    @SerializedName("payment")
    private Payment payment;

    @SerializedName("integrationType")
    private String integrationType;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultInfo() {
        return resultInfo;
    }

    public void setResultInfo(String resultInfo) {
        this.resultInfo = resultInfo;
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Interaction getInteraction() {
        return interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public Networks getNetworks() {
        return networks;
    }

    public void setNetworks(Networks networks) {
        this.networks = networks;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

}
