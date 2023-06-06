package schedule.pro.application.Entity.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestClockingDto {

    public Long userId;
    public String taskName;
    public int day;
    public int month;
    public int year;
    public int hours;

}
