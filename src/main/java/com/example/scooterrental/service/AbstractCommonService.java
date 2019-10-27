package com.example.scooterrental.service;

import com.example.scooterrental.common.MsgSource;

public abstract class AbstractCommonService {

    protected MsgSource msgSource;

    public AbstractCommonService() {
    }

    public AbstractCommonService(MsgSource msgSource) {
        this.msgSource = msgSource;
    }

}
