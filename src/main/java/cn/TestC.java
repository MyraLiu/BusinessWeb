package cn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestC {
    @RequestMapping("/test")
    public void test(){
        System.out.println("======testc======");
        return ;
    }
}
