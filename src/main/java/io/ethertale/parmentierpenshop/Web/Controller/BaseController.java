package io.ethertale.parmentierpenshop.Web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    @GetMapping
    @RequestMapping("/")
    public ModelAndView mainPage(){
        ModelAndView mav = new ModelAndView("index");



        return mav;
    }
}
