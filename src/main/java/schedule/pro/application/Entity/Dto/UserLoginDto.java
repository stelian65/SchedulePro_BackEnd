package schedule.pro.application.Entity.Dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.pro.application.Entity.Role;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    private Integer Id;
    private String email;
    private String firstname;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Role role;

}
