package com.example.scooterrental.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MsgSource {

    public final String OK001;
    public final String OK002;
    public final String OK003;
    public final String OK004;

    public final ConstErrorMsg ERR001;
    public final ConstErrorMsg ERR002;
    public final ConstErrorMsg ERR003;
    public final ConstErrorMsg ERR004;
    public final ConstErrorMsg ERR005;
    public final ConstErrorMsg ERR006;
    public final ConstErrorMsg ERR007;
    public final ConstErrorMsg ERR008;
    public final ConstErrorMsg ERR009;
    public final ConstErrorMsg ERR010;
    public final ConstErrorMsg ERR011;
    public final ConstErrorMsg ERR012;
    public final ConstErrorMsg ERR013;

    public MsgSource(
            @Value("${common.ok.msg.ok001}") String ok001MsgValue,
            @Value("${common.ok.msg.ok002}") String ok002MsgValue,
            @Value("${common.ok.msg.ok003}") String ok003MsgValue,
            @Value("${common.ok.msg.ok004}") String ok004MsgValue,

            @Value("${common.const.error.msg.err001}") String err001MsgValue,
            @Value("${common.const.error.msg.err002}") String err002MsgValue,
            @Value("${common.const.error.msg.err003}") String err003MsgValue,
            @Value("${common.const.error.msg.err004}") String err004MsgValue,
            @Value("${common.const.error.msg.err005}") String err005MsgValue,
            @Value("${common.const.error.msg.err006}") String err006MsgValue,
            @Value("${common.const.error.msg.err007}") String err007MsgValue,
            @Value("${common.const.error.msg.err008}") String err008MsgValue,
            @Value("${common.const.error.msg.err009}") String err009MsgValue,
            @Value("${common.const.error.msg.err010}") String err010MsgValue,
            @Value("${common.const.error.msg.err011}") String err011MsgValue,
            @Value("${common.const.error.msg.err012}") String err012MsgValue,
            @Value("${common.const.error.msg.err013}") String err013MsgValue
    ) {
        OK001 = ok001MsgValue;
        OK002 = ok002MsgValue;
        OK003 = ok003MsgValue;
        OK004 = ok004MsgValue;

        ERR001 = new ConstErrorMsg("Err001", err001MsgValue);
        ERR002 = new ConstErrorMsg("Err002", err002MsgValue);
        ERR003 = new ConstErrorMsg("Err003", err003MsgValue);
        ERR004 = new ConstErrorMsg("Err004", err004MsgValue);
        ERR005 = new ConstErrorMsg("Err005", err005MsgValue);
        ERR006 = new ConstErrorMsg("Err006", err006MsgValue);
        ERR007 = new ConstErrorMsg("Err007", err007MsgValue);
        ERR008 = new ConstErrorMsg("Err008", err008MsgValue);
        ERR009 = new ConstErrorMsg("Err009", err009MsgValue);
        ERR010 = new ConstErrorMsg("Err010", err010MsgValue);
        ERR011 = new ConstErrorMsg("Err011", err011MsgValue);
        ERR012 = new ConstErrorMsg("Err012", err012MsgValue);
        ERR013 = new ConstErrorMsg("Err013", err013MsgValue);

    }


}
