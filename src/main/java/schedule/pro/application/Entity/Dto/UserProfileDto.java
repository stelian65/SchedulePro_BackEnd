package schedule.pro.application.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private String firstname;
    private String lastname;
    private String studies;
    private String experience;
    private String email;
    private String phoneNumber;
    private List<TaskUserProfileDto> tasks;
    private List<RequestUserProfileDto>  request;
    private List<MonthHoursDto> worked;

}
