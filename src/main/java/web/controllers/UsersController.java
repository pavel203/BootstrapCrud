package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.models.User;
import web.services.UserService;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String index(Model model) { //allUsers
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users/success";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user);
        return "redirect:/users/success";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/users/success";
    }




    /*@GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", new User());

        return "users/new";
    }

     */

    /*@PostMapping("/users")
    public String create(@RequestParam("name") String name,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("age") int age,
                         Model model) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        userService.addUser(user);
        return "users/new";
    }

     */

}
