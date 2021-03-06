package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String main() {
        return "index";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        //Получим список пользователей и передадим в представление
        System.out.println(userService.getAllUsers());
        model.addAttribute("users", userService.getAllUsers());
        return "users/showAll";
    }

    @GetMapping("/users/{id}")
    public String getCurrentUser(@PathVariable("id") int id, Model model) {
        //Получим одного пользователя по id и передадим на представление
        model.addAttribute("user", userService.getCurrentUser(id));
        return "users/show";
    }

    @GetMapping("/users/new")
    public String addNewUser(@ModelAttribute("user") User user) {
        //Вернет html форму для создания нового пользователя
        return "users/new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/new";
        userService.addNewUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(Model model, @PathVariable("id") int id) {
        //Вернет html форму для редактирования страницы пользователя
        model.addAttribute("user", userService.getCurrentUser(id));
        return "users/edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        //Обновляет пользователя
        if (bindingResult.hasErrors())
            return "users/edit";
        userService.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        //Удаляет пользователя
        userService.delete(id);
        return "redirect:/users";
    }
}