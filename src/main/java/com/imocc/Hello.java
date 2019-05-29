package com.imocc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kqyang on 2019/5/29.
 */
@RestController
public class Hello {
    @RequestMapping(name = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello SpringBoot";
    }
}
