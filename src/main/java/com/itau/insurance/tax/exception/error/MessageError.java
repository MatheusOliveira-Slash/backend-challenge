
package com.itau.insurance.tax.exception.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageError {

    private String error;
    private String key;

    public MessageError(String error) {
        this.error = error;
    }

    public MessageError(String error, String key) {
        this.error = error;
        this.key = key;
    }
}
