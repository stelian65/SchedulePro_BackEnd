package schedule.pro.application.Entity.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.pro.application.Entity.TaskStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    Long id;
    String title;
    String description;
    String userAssign;
    String dueDate;
    TaskStatus taskStatus;
    float totalHours;

}
