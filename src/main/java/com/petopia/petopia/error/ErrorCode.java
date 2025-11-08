package com.petopia.petopia.error;

public enum ErrorCode implements ErrorHandle{


    CODE_2001(2001,"Something went wrong..."),
    CODE_2002(2002,"No User Found..."),
    CODE_2003(2003, "Not Authorized to Update this User...");


    private final int errorCode;

    private final String message;


    ErrorCode(int errorCode,String message){
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
