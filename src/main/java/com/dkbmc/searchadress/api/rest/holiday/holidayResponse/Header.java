package com.dkbmc.searchadress.api.rest.holiday.holidayResponse;

import javax.xml.bind.annotation.XmlElement;

public class Header {
    private String resultCode;
    private String resultMsg;
    private String errMsg;

    @XmlElement(name = "errMsg")
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @XmlElement(name = "returnReasonCode")
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @XmlElement(name = "returnAuthMsg")
    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
