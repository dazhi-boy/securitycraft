package com.dazhi.securitycraft.contraller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class B {
    @RequestMapping("b")
    @ResponseBody
    public String show(){
        return "b";
    }
}
