package com.zooplus.sdc.converter.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @GetMapping("/")
    public void home() {
    }

    @GetMapping("/secured")
    public void secured() {
    }
}
