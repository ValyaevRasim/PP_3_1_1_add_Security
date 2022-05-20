package spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring_boot.entity.Role;
import spring_boot.entity.User;
import spring_boot.service.RoleService;
import spring_boot.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {

    private final UserServiceImpl userServiceImpl;
    private final RoleService roleService;

    @Autowired
    public MyController(UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }


    // начальная страница
    @RequestMapping("/")
    public String showAllUsers(Model model) {
        System.out.println("showAllUsers/allUsers");
        List<User> allUsers = userServiceImpl.getAllUsers();
        model.addAttribute("userList", allUsers);
        return "allUsers";
    }

    // добавление нового пользователяю, используем 2 метода
    @RequestMapping("/admin/addUser")
    public String addNewUser(Model model) {
        System.out.println("addUser/new");
        List<Role> roles = roleService.listRoles();
        model.addAttribute("user", new User());
        model.addAttribute("allRoles",roles);
        return "newUser";
    }

    @PostMapping("/admin/saveUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        System.out.println("createNewUser");
        userServiceImpl.saveUser(user);
        return "redirect:/";
    }

    //    обновление данных пользователя, используем 2 метода
    @GetMapping("/admin/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, Model model){
        System.out.println("updateUser/updateUser");
        User user = userServiceImpl.getUserById(id);
        List<Role> roles = roleService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("allRoles",roles);
        return "editUser";
    }

    @RequestMapping("/{id}")
    public String edit(@ModelAttribute("user") User user) {
        System.out.println("edit");
        System.out.println(user.getRoles());
        userServiceImpl.saveUser(user);
        return "redirect:/";
    }

    //    удаление пользователя, используем 2 метода
    @GetMapping("/admin/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id){
        System.out.println("deleteUser/deleteUser");
        userServiceImpl.deleteUserById(id);
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
        roleService.saveRole(role);
        return "redirect:/";
    }

}
