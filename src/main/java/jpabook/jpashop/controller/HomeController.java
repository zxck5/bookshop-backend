package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

    // logger

    @GetMapping(value = {"/","/home"})
    public String home() {
        return "Basic Shop";
    }


}
