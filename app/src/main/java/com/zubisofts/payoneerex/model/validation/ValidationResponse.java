
package com.zubisofts.payoneerex.model.validation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ValidationResponse {

    @SerializedName("valid")
    @Expose
    private Boolean valid;
    @SerializedName("messages")
    @Expose
    private Messages messages;

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

}
