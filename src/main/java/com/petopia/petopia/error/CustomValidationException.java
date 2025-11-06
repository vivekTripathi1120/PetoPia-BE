package com.petopia.petopia.error;

import com.mysql.cj.util.StringUtils;

public class CustomValidationException extends IllegalArgumentException{

    private final ErrorCode errorCode;

    public CustomValidationException(ErrorCode errorCode) {
        super(getMessage(errorCode));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode(){
        return errorCode;
    }

    private static String getMessage(ErrorCode errorCode){
        if(!StringUtils.isNullOrEmpty(errorCode.getMessage())){
            return errorCode.getMessage();
        }
        return null;
    }
}
