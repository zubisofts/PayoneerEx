
package com.zubisofts.payoneerex.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Networks implements Serializable {

    @SerializedName("applicable")
    private List<Applicable> applicable = null;

    public List<Applicable> getApplicable() {
        return applicable;
    }

    public void setApplicable(List<Applicable> applicable) {
        this.applicable = applicable;
    }

}
