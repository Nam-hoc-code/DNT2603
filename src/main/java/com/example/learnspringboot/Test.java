package com.example.learnspringboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/t xest")
public class Test {

   @RequestMapping("/hh")
   public String index() {
       return "Hello World";
   }
}
