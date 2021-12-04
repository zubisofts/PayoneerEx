
package com.zubisofts.payoneerex.model.validation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Messages {

    @SerializedName("expiryDate")
    @Expose
    private ExpiryDate expiryDate;
    @SerializedName("number")
    @Expose
    private Number number;
    @SerializedName("holderName")
    @Expose
    private HolderName holderName;
    @SerializedName("expiryMonth")
    @Expose
    private ExpiryMonth expiryMonth;
    @SerializedName("expiryYear")
    @Expose
    private ExpiryYear expiryYear;
    @SerializedName("verificationCode")
    @Expose
    private VerificationCode verificationCode;
    @SerializedName("bis")
    @Expose
    private Bis bis;

    public ExpiryDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(ExpiryDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public HolderName getHolderName() {
        return holderName;
    }

    public void setHolderName(HolderName holderName) {
        this.holderName = holderName;
    }

    public ExpiryMonth getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(ExpiryMonth expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public ExpiryYear getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(ExpiryYear expiryYear) {
        this.expiryYear = expiryYear;
    }

    public VerificationCode getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(VerificationCode verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Bis getBis() {
        return bis;
    }

    public void setBis(Bis bis) {
        this.bis = bis;
    }

}
