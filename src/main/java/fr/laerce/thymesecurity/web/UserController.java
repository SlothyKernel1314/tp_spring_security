package fr.laerce.thymesecurity.web;


import fr.laerce.thymesecurity.security.dao.UserDao;
import fr.laerce.thymesecurity.security.domain.User;
import fr.laerce.thymesecurity.security.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserDao userDao;

    @Autowired
    JpaUserService jpaUserService;

    @GetMapping("admin/users")
    public String list(Model model){
        Iterable<User> users = jpaUserService.findAllUsers();
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/recovery/{id}")
    public String recovery(Model model, @PathVariable("id") long id){
        model.addAttribute("user", jpaUserService.findByUserId(id));
        model.addAttribute("idUser", id);
        return "recovery";
    }
}
