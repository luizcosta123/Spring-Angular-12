package com.luiz.helpdesk.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> listError = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public List<FieldMessage> getListError() {
        return listError;
    }

    public void addError(String fieldName, String message) {
        this.listError.add(new FieldMessage(fieldName, message));
    }
}
