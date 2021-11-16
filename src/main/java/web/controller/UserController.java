package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        return "admin/users";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("role", roleService.getAllRoles());
        return "admin/new";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user, @RequestParam("roles") String[] roles) {
        user.setRoles(roleService.getRoleSet(roles));
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{username}/edit")
    public String edit(Model model, @PathVariable("username") String username) {
        model.addAttribute("user", userService.findUserByUsername(username));
        model.addAttribute("role", roleService.getAllRoles());
        return "admin/edit";
    }

    @PostMapping("/admin/{username}")
    public String update(@ModelAttribute("user") User user, @PathVariable("username") String username,
                         @RequestParam("roles") String[] roles) {
        user.setRoles(roleService.getRoleSet(roles));
        userService.update(username, user);
        return "redirect:/admin";
    }



    @GetMapping("/admin/{username}")
    public String show(@PathVariable("username") String username, Model model) {
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        //model.addAttribute("roles", roleService.getRoles(user));
        return "admin/show";
    }

    @DeleteMapping("/admin/{username}")
    public String delete(@PathVariable("username") String username) {
        userService.delete(username);
        return "redirect: /admin";
    }

//    @RequestMapping(value = "hello", method = RequestMethod.GET)
//    public String printWelcome(ModelMap model) {
//        List<String> messages = new ArrayList<>();
//        messages.add("Hello!");
//        messages.add("I'm Spring MVC-SECURITY application");
//        messages.add("5.2.0 version by sep'19 ");
//        model.addAttribute("messages", messages);
//        return "hello";
//    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String showUserInfo(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        model.addAttribute("currentUser", userService.findUserByUsername(currentUser.getUsername()));
        return "/user/info";
    }


}
