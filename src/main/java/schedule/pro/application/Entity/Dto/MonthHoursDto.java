package schedule.pro.application.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthHoursDto {
    private String month;
    private float hours;
    private int totalHours;
}
