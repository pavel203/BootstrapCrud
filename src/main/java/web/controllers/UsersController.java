package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.repository.RoleRepository;
import web.models.User;
import web.services.UserService;
import java.util.List;

@Controller
@RequestMapping("") //?
public class UsersController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/index";
    }

    @GetMapping("/user")
    public String showSingleUser(Model model) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", principal);
        return "users/show";

    }

    @GetMapping("/admin/new") //
    public String showFormForNewUser(Model model) {
        model.addAttribute("ROLE_USER", roleRepository.findById(1).get());
        model.addAttribute("ROLE_ADMIN", roleRepository.findById(2).get());
        model.addAttribute("user", new User());
        return "admin/new";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/edit")
    public String showEditForm(Model model, @PathVariable("id") int id) {
        model.addAttribute("ROLE_USER", roleRepository.findById(1).get());
        model.addAttribute("ROLE_ADMIN", roleRepository.findById(2).get());
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @PostMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/{id}/delete")
    public String delete(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/delete";
    }

    @PostMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

}
