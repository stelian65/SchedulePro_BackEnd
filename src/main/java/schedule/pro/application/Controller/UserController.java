package schedule.pro.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schedule.pro.application.Entity.Dto.*;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.InvalidPasswordException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
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
    public ResponseEntity<User> assignTask(@RequestParam String email,@RequestParam String title) throws TaskNotFoundException, UserNotFoundException {
        return  new ResponseEntity<User>(userService.assignTask(title,email),HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<User> getUserByUsername(@RequestParam String email) throws UserNotFoundException {
        return  new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity<UserProfileDto> getUserProfile(@RequestParam int userId) throws UserNotFoundException{
       UserProfileDto userProfileDto = userService.getUserProfile(userId);
       return  new ResponseEntity<>(userProfileDto,HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<UserViewDto>> getAllUsers() {
       List<UserViewDto> users =  userService.getAllUsers();
       return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/editUser")
    @ResponseBody
    public ResponseEntity<EditUserDto> getUserDtoForEdit(@RequestParam int userId) throws UserNotFoundException {
        EditUserDto user = userService.getEditUser(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/edit")
    @ResponseBody
    public ResponseEntity<Boolean> editUser(@RequestBody EditUserDto user) throws  UserNotFoundException{
        Boolean response  = userService.editUser(user);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<Boolean> deleteUser(@RequestParam int userId) throws UserNotFoundException{
        Boolean response = userService.deleteUser(userId);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/selection")
    @ResponseBody
    public ResponseEntity<List<SelectUserDto>> getSelectUsersDto() {
        List<SelectUserDto> users = userService.getAllUserForSelect();
        return  new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PutMapping("/password")
    @ResponseBody
    public ResponseEntity<Boolean> changeUserPassword(@RequestBody ChangePasswordDto changePasswordDto) throws UserNotFoundException, InvalidPasswordException {
        Boolean response = userService.changePassword(changePasswordDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


}
