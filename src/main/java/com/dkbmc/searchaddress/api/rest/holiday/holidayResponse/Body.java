package com.dkbmc.searchaddress.api.rest.holiday.holidayResponse;

import javax.xml.bind.annotation.XmlElement;

public class Body {
    private ItemsWrapper items;
    private int numOfRows;
    private int pageNo;
    private int totalCount;

    @XmlElement(name = "items")
    public ItemsWrapper getItems() {
        return items;
    }

    public void setItems(ItemsWrapper items) {
        this.items = items;
    }

    @XmlElement(name = "numOfRows")
    public int getNumOfRows() {
        return numOfRows;
    }

    public void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }

    @XmlElement(name = "pageNo")
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @XmlElement(name = "totalCount")
    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
