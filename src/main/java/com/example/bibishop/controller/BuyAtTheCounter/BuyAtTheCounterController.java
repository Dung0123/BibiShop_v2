package com.example.bibishop.controller.BuyAtTheCounter;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyAtTheCounterController {

    @GetMapping("/buyAtTheCounter")
    public String buyAtTheCounter(Model model) {
        return "admin/BuyAtTheCounter/buy-at-the-counter";
    }

}
