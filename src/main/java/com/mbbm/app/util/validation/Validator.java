package com.mbbm.app.util.validation;

import com.mbbm.app.http.request.RequestDTO;

public interface Validator {
    void validate(RequestDTO requestDTO);
}
