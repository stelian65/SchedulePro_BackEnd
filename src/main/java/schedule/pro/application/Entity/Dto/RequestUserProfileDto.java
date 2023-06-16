package schedule.pro.application.Entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.pro.application.Entity.RequestType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserProfileDto {
    private String from;
    private String to;
    private boolean pending;
    private boolean status;
    private RequestType type;

}
