package io.ethertale.parmentierpenshop.Web.Controller;

import io.ethertale.parmentierpenshop.Model.User;
import io.ethertale.parmentierpenshop.Security.AuthenticationDetails;
import io.ethertale.parmentierpenshop.Service.UserService;
import io.ethertale.parmentierpenshop.Web.Dto.LoginUserDto;
import io.ethertale.parmentierpenshop.Web.Dto.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

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
        mav.addObject("loginDto", new LoginUserDto());
        return mav;
    }

    @GetMapping("/profile/{id}")
    public ModelAndView profilePage(@PathVariable UUID id, @AuthenticationPrincipal AuthenticationDetails authenticationDetails){
        ModelAndView mav = new ModelAndView("profile");

        User loggedUser = userService.getUserById(authenticationDetails.getId());

        mav.addObject("loggedUser", loggedUser);

        return mav;
    }
}
