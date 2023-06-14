package com.dkbmc.searchaddress.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @GetMapping({"/", ""})
    public String indexPage() {
        return "page/index";
    }

    @GetMapping("/holiday/create")
    public String holidayCreatePage() { return "page/holidayCreate"; }

    @GetMapping("/holiday/update/{id}")
    public String holidayUpdatePage(@PathVariable Long id) { return "page/holidayUpdate"; }

}
