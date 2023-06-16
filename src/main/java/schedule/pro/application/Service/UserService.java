package schedule.pro.application.Service;

import schedule.pro.application.Entity.Dto.*;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.InvalidPasswordException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;

import java.util.List;


public interface  UserService {
    User saveUser(User user);
    User findByEmail(String email) throws UserNotFoundException;
    User assignTask(String title,String email) throws TaskNotFoundException, UserNotFoundException;
    UserProfileDto getUserProfile(int userId) throws  UserNotFoundException;
    List<UserViewDto> getAllUsers() ;
    EditUserDto getEditUser(int userId) throws UserNotFoundException;
    boolean editUser(EditUserDto userDto) throws UserNotFoundException;
    boolean deleteUser(int userId) throws UserNotFoundException;
    List<SelectUserDto> getAllUserForSelect();
    boolean changePassword(ChangePasswordDto changePasswordDto) throws InvalidPasswordException, UserNotFoundException;
}
