
package com.zubisofts.payoneerex.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContractData implements Serializable {

    @SerializedName("PAGE_ENVIRONMENT")
    private String pageEnvironment;

    @SerializedName("JAVASCRIPT_INTEGRATION")
    private String javascriptIntegration;

    @SerializedName("PAGE_BUTTON_LOCALE")
    private String pageButtonLocale;

    public String getPageEnvironment() {
        return pageEnvironment;
    }

    public void setPageEnvironment(String pageEnvironment) {
        this.pageEnvironment = pageEnvironment;
    }

    public String getJavascriptIntegration() {
        return javascriptIntegration;
    }

    public void setJavascriptIntegration(String javascriptIntegration) {
        this.javascriptIntegration = javascriptIntegration;
    }

    public String getPageButtonLocale() {
        return pageButtonLocale;
    }

    public void setPageButtonLocale(String pageButtonLocale) {
        this.pageButtonLocale = pageButtonLocale;
    }

}
