package schedule.pro.application.Entity.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.pro.application.Entity.RequestType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestDto {
    private Integer userId;
    private String from;
    private String to;
    private RequestType type;


}
