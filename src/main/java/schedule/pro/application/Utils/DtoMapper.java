package schedule.pro.application.Utils;


import schedule.pro.application.Entity.Clocking;
import schedule.pro.application.Entity.Dto.*;
import schedule.pro.application.Entity.Request;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class DtoMapper {
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{10,}$");

    public static Clocking FromDtoToClocking (RequestClockingDto clockingDto) {
        Clocking clocking = Clocking.builder()
                .day(clockingDto.getDay())
                .month(clockingDto.getMonth())
                .year(clockingDto.getYear())
                .hours(clockingDto.getHours())
                .build();
        return  clocking;
    }
    public static TaskDto fromTaskToTaskDto(Task task) {
        TaskDto  taskDto = TaskDto.builder()
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .title(task.getTitle())
                .taskStatus(task.getStatus())
                .id(task.getId())
                .totalHours(task.getTotalHours())
                .build();
        if(task.getUser()!=null){
            taskDto.setUserAssign(task.getUser().getFirstname()+" "+ task.getUser().getLastname());
        }
        return  taskDto;
    }

    public static Request fromCreateRequestDtoToRequest(CreateRequestDto createRequestDto){
        Request request = Request.builder()
                .from(createRequestDto.getFrom())
                .to(createRequestDto.getTo())
                .type(createRequestDto.getType())
                .pending(true)
                .build();
        return request;
    }

    public static List<TaskUserProfileDto> fromTaskListToTaskUserProfileDtoList(List<Task> tasks){
        List<TaskUserProfileDto> tasksDto = new ArrayList<>();
        if(tasks == null){
            return  tasksDto;
        }
        tasks.forEach((task -> {
            TaskUserProfileDto taskDto = TaskUserProfileDto
                    .builder()
                    .status(task.getStatus())
                    .dueDate(task.getDueDate())
                    .title(task.getTitle())
                    .build();
            tasksDto.add(taskDto);
        }));
        return tasksDto;
    }

    public static List<RequestUserProfileDto> fromRequestListToRequestUserProfileDto(List<Request> requests){
       List<RequestUserProfileDto> requestsDto = new ArrayList<>();
       if(requests == null){
           return  requestsDto;
       }
       requests.forEach((request -> {
           RequestUserProfileDto reguestDto = RequestUserProfileDto.builder()
                   .from(request.getFrom())
                   .to(request.getTo())
                   .pending(request.isPending())
                   .type(request.getType())
                   .status(request.isResponse())
                   .build();
           requestsDto.add(reguestDto);
       }));
        return requestsDto;

    }

    public static List<MonthHoursDto> fromClockingToRequests(List<Clocking> clockings){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        int[] totalhours = new int[]{160,160,184,144,176,160,168,176,168,176,168,144};
        List<MonthHoursDto> monthHoursDtoList = new ArrayList<>();
        float hours =0;
        for(int i=0 ;i<months.length;i++) {
            MonthHoursDto monthHoursDto = MonthHoursDto.builder()
                    .month(months[i])
                    .totalHours(totalhours[i])
                    .hours(0)
                    .build();
            if (clockings != null)
                for (Clocking clocking : clockings) {
                    if (clocking.getMonth() == i+1){
                        hours += clocking.getHours();
                    }
                }
            monthHoursDto.setHours(hours);
                hours=0;
            monthHoursDtoList.add(monthHoursDto);
            }
        return monthHoursDtoList;
    }

    public static List<UserViewDto> fromUsertoUserViewDto(List<User> users){
        List<UserViewDto> userViewDtos = new ArrayList<>();
        if(users == null ){
            return userViewDtos;
        }
        for(User user : users){
            UserViewDto userdto = UserViewDto.builder()
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .id(user.getId())
                    .build();
            userViewDtos.add(userdto);
        }
        return userViewDtos;
    }

    public static EditUserDto fromUserToEditUserDto(User user){
        EditUserDto userDto = EditUserDto.builder()
                .role(user.getRole())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .experience(user.getExperience())
                .studies(user.getStudies())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .id(user.getId())
                .build();
        return  userDto;
    }

    public static List<SelectUserDto> fromUserListToSelectUserDtoList(List<User> users){
        List<SelectUserDto> usersDto = new ArrayList<>();
        if(users == null){
            return usersDto;
        }
        for(User user :users){
            SelectUserDto userDto = SelectUserDto.builder()
                    .id(user.getId())
                    .name(user.getFirstname()+" " +user.getLastname())
                    .build();
            usersDto.add(userDto);
        }
        return  usersDto;
    }



    public static boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

}
