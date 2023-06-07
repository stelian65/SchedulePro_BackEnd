package schedule.pro.application.Utils;


import schedule.pro.application.Entity.Clocking;
import schedule.pro.application.Entity.Dto.RequestClockingDto;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.Dto.TaskDto;

public class DtoMapper {

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

}
