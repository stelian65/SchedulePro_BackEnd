package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.pro.application.Entity.Dto.*;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.InvalidPasswordException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Repository.TaskRepository;
import schedule.pro.application.Repository.TokenRepository;
import schedule.pro.application.Repository.UserRepository;
import schedule.pro.application.Service.UserService;
import schedule.pro.application.Utils.DtoMapper;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;
    TaskRepository taskRepository;
    TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, EmailService emailService){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.tokenRepository =tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException());
    }


    @Override
    public User assignTask(String title,String email) throws TaskNotFoundException, UserNotFoundException {
        Task task = taskRepository.findByTitle(title).orElseThrow(() -> new TaskNotFoundException());
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        user.addTask(task);
        task.setUser(user);
        taskRepository.save(task);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserProfileDto getUserProfile(int userId) throws UserNotFoundException {
        User user=   userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        UserProfileDto userProfileDto = UserProfileDto.builder()
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .experience(user.getExperience())
                .phoneNumber(user.getPhoneNumber())
                .studies(user.getStudies())
                .build();
        List<TaskUserProfileDto> tasks = DtoMapper.fromTaskListToTaskUserProfileDtoList(user.getTasks());
        userProfileDto.setTasks(tasks);
        List<RequestUserProfileDto> requests = DtoMapper.fromRequestListToRequestUserProfileDto(user.getRequests());
        userProfileDto.setRequest(requests);
        List<MonthHoursDto> monthHoursDtoList = DtoMapper.fromClockingToRequests(user.getClockings());
        userProfileDto.setWorked(monthHoursDtoList);
        return userProfileDto;
    }

    @Override
    public List<UserViewDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserViewDto> usersDto = DtoMapper.fromUsertoUserViewDto(users);
        return  usersDto;
    }

    @Override
    public EditUserDto getEditUser(int userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        EditUserDto userDto = DtoMapper.fromUserToEditUserDto(user);
        return  userDto;
    }

    @Override
    public boolean editUser(EditUserDto userDto) throws UserNotFoundException {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setRole(userDto.getRole());
        user.setExperience(userDto.getExperience());
        user.setStudies(userDto.getStudies());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteUser(int userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        tokenRepository.deleteByUserId(userId);
        List<Task> tasks = user.getTasks();
        if(tasks != null ){
            if(!tasks.isEmpty()){
                for(Task task : tasks){
                    task.setUser(null);
                    taskRepository.save(task);
                }
            }
        }
        user.setTasks(null);
        userRepository.save(user);
        userRepository.delete(user);
        return true;
    }

    @Override
    public List<SelectUserDto> getAllUserForSelect() {
        List<User> users = userRepository.findAll();
        List<SelectUserDto> usersDto = DtoMapper.fromUserListToSelectUserDtoList(users);
        return usersDto;
    }

    @Override
    public boolean changePassword(ChangePasswordDto changePasswordDto) throws InvalidPasswordException, UserNotFoundException {
        User user = userRepository.findById(changePasswordDto.getId()).orElseThrow(() -> new UserNotFoundException());
        if(!passwordEncoder.matches(changePasswordDto.getOldPassword(),user.getPassword())){
            throw new InvalidPasswordException();
        }
        if(!changePasswordDto.getNewPassword().equals(changePasswordDto.getNewRepeatPassword())){
            throw new InvalidPasswordException();
        }
        if(!DtoMapper.validatePassword(changePasswordDto.getNewPassword())){
            throw new InvalidPasswordException();
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        emailService.sendEmailChangePassword(user.getEmail(),changePasswordDto.getNewPassword());
        userRepository.save(user);
        return true;

    }


}
