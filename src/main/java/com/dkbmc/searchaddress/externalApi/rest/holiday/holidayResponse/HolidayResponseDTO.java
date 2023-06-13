package com.dkbmc.searchaddress.externalApi.rest.holiday.holidayResponse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class HolidayResponseDTO {
    private Header header;
    private Body body;

    @XmlElement(name = "header")
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @XmlElement(name = "body")
    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
