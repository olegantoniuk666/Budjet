package com.reckless.testsandbox;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Aleksandr on 7/21/15.
 */

@Controller
public class TestApiContoller {

    //Added test page to test API's
    @RequestMapping("/testAPI")
    public String testApi(Model model) {
        model.addAttribute("greeting", "Hello. This is API test page");
        return "testAPI";
    }
    
}