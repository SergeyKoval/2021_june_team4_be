package com.exadel.discount.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class HomeController {
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Mapping added to test date time functionalities of functionalities using @DateTimeFormat
    @GetMapping("/datetime")
    @ResponseBody
    public String dateTime(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                           @RequestParam("datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime datetime) {
        return date.toString() + "<br>" + datetime.toString();
    }
}