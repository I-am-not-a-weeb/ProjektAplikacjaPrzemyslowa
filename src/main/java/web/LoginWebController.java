package web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginWebController {


    @RequestMapping("/login")
    public String showLoginPage()
    {
        return "login";
    }
}
