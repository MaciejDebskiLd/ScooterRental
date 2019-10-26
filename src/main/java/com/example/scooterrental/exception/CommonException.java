package com.example.scooterrental.exception;

import com.example.scooterrental.common.ConstErrorMsg;

public class CommonException extends RuntimeException {

    private ConstErrorMsg constErrorMsg;

    public CommonException(ConstErrorMsg constErrorMsg) {
        this.constErrorMsg = constErrorMsg;
    }

    public ConstErrorMsg getConstErrorMsg() {
        return constErrorMsg;
    }
}
