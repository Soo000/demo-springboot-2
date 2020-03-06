package com.alisls.demo.springboot.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ResourceController {

    @GetMapping("/r/r1")
    public String reource1() {
        return "访问r/r1";
    }

    @GetMapping("/r/r2")
    public String reource2() {
        return "访问r/r2";
    }
}
