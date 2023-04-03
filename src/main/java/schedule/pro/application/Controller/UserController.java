package schedule.pro.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

     private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/createNewAccount")
    @ResponseBody
     public User saveUser(@RequestBody User user){
         return userService.saveUser(user);
    }
}
