package com.dkbmc.searchaddress.api.rest.holiday.holidayResponse;


import javax.xml.bind.annotation.XmlElement;

public class Item {
    private String dateKind;
    private String dateName;
    private String isHoliday;
    private int locdate;
    private int seq;

    @XmlElement(name = "dateKind")
    public String getDateKind() { return dateKind; }

    public void setDateKind(String dateKind) { this.dateKind = dateKind; }

    @XmlElement(name = "dateName")
    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }

    @XmlElement(name = "isHoliday")
    public String getIsHoliday() {
        return isHoliday;
    }

    public void setIsHoliday(String isHoliday) {
        this.isHoliday = isHoliday;
    }

    @XmlElement(name = "locdate")
    public int getLocdate() {
        return locdate;
    }

    public void setLocdate(int locdate) {
        this.locdate = locdate;
    }

    @XmlElement(name = "seq")
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    // getter and setter methods for all fields with @XmlElement annotations
}
