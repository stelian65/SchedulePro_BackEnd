package schedule.pro.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Service.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

     private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/createNewAccount")
    @ResponseBody
     public ResponseEntity<User> saveUser(@RequestBody User user){
        User userResponse = userService.saveUser(user);
         return new ResponseEntity<User>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/assignTask")
    @ResponseBody
    public ResponseEntity<User> assignTask(@RequestParam String username,@RequestParam String name) throws TaskNotFoundException {
        return  new ResponseEntity<User>(userService.assignTask(name,username),HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<User> getUserByUsername(@RequestParam String username){
        return  new ResponseEntity<>(userService.getUserByUsername(username),HttpStatus.OK);
    }

}
