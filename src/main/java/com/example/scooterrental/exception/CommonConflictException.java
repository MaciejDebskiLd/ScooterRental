package com.example.scooterrental.exception;

import com.example.scooterrental.common.ConstErrorMsg;

public class CommonConflictException extends CommonException {

    public CommonConflictException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
