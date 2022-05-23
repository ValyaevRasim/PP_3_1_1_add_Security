package spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring_boot.entity.Role;
import spring_boot.entity.User;
import spring_boot.service.RoleServiceImpl;
import spring_boot.service.SecurityService;
import spring_boot.service.UserDetailServiceImpl;
import spring_boot.service.UserService;
import spring_boot.validator.UserValidator;

import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {

    private final UserDetailServiceImpl userDetailServiceImpl;
    private final RoleServiceImpl roleServiceImpl;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

//    @Autowired
//    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    public MyController(UserDetailServiceImpl userDetailServiceImpl, RoleServiceImpl roleServiceImpl) {
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

//
//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("userForm") User user, BindingResult bindingResult, Model model) {
//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//        userService.saveUser(user);
//        securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());
//        return "redirect:/welcome";
//    }
//
//    @GetMapping("/login")
//    public String login(Model model, String error, String logout) {
//        if (error != null) {
//            model.addAttribute("error", "Username or password is incorrect");
//        }
//        if (logout != null) {
//            model.addAttribute("message", "Logged out successfully");
//        }
//        return "login";
//    }
//
//    @GetMapping({"/", "/welcome"})
//    public String welcome(Model model) {
//        return "welcome";
//    }
//
//    @GetMapping("/admin")
//    public String admin(Model model) {
//        return "admin";
//    }

    // начальная страница
    @RequestMapping("/")
    public String showAllUsers(Model model) {
        System.out.println("showAllUsers/allUsers");
        List<User> allUsers = userDetailServiceImpl.getAllUsers();
        model.addAttribute("userList", allUsers);
        return "allUsers";
    }

    // добавление нового пользователяю, используем 2 метода
    @RequestMapping("/admin/addUser")
    public String addNewUser(Model model) {
        System.out.println("addUser/new");
        List<Role> roles = roleServiceImpl.getAllRoles();
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roles);
        return "newUser";
    }

    @PostMapping("/admin/saveUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        System.out.println("createNewUser");
        userDetailServiceImpl.saveUser(user);
        return "redirect:/";
    }

    //    обновление данных пользователя, используем 2 метода
    @GetMapping("/admin/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        System.out.println("updateUser/updateUser");
        User user = userDetailServiceImpl.getUserById(id);
        List<Role> roles = (List<Role>) roleServiceImpl.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roles);
        return "editUser";
    }

    @RequestMapping("/{id}")
    public String edit(@ModelAttribute("user") User user) {
        System.out.println("edit");
        userDetailServiceImpl.saveUser(user);
        return "redirect:/";
    }

    //    удаление пользователя, используем 2 метода
    @GetMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        System.out.println("deleteUser/deleteUser");
        userDetailServiceImpl.deleteUserById(id);
        return "redirect:/";
    }

    // добавление новой роли, используем 2 метода
//    @RequestMapping("/addRole")
    @GetMapping("/admin/addRole")
    public String newRolePage(Model model) {
        System.out.println("addNewRole/new");
        model.addAttribute("role", new Role());
        return "newRole";
    }

    //    @PostMapping()
//    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @PostMapping("/admin/saveRole")
    public String saveRole(@ModelAttribute("role") Role role) {
        System.out.println("createNewRole");
        roleServiceImpl.saveRole(role);
        return "redirect:/";
    }

}
