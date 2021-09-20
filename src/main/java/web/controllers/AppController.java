package web.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String showMainPage() {
        return "mainPage";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        return "/errors/alreadyLoggedIn";
    }


//    @GetMapping("/")
//    public String showMainPage(Model model) {
//        //List<User> users = userService.getAllUsers();
//        //model.addAttribute("users", users);
//        //User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        //model.addAttribute("principal", principal);
//        return "mainPage";
//    }
}
