package com.dkbmc.searchaddress.api.rest.holiday.holidayResponse;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class ItemsWrapper {
    private List<Item> items;

    @XmlElement(name = "item")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
