package io.ethertale.parmentierpenshop.Web.Controller;

import io.ethertale.parmentierpenshop.Service.UserService;
import io.ethertale.parmentierpenshop.Web.Dto.LoginUserDto;
import io.ethertale.parmentierpenshop.Web.Dto.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    private final UserService userService;

    @Autowired
    public BaseController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView mainPage(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @GetMapping("/register")
    public ModelAndView registerPage(){
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("registerDto", new RegisterUserDto());
        return mav;
    }

    @PostMapping("/register")
    public String registerUser(RegisterUserDto registerUserDto){
        userService.registerUser(registerUserDto);
        return "redirect:/";
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("loginDTO", new LoginUserDto());
        return mav;
    }
}
