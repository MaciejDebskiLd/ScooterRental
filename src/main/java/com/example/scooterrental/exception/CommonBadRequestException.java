package com.example.scooterrental.exception;

import com.example.scooterrental.common.ConstErrorMsg;

public class CommonBadRequestException extends CommonException {

    public CommonBadRequestException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
