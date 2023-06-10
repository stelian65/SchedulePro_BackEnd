package schedule.pro.application.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.pro.application.Entity.TaskStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditTaskDto {
    Long taskId;
    String title;
    String description;
    String dueDate;
    Long userId;
    TaskStatus status;
}
