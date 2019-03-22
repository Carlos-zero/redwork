package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JsonController {
    @ResponseBody
    public String failure(){
        return "您无权进行此操作，are you carry？";
    }
}
