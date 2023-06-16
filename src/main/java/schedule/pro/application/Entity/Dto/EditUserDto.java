package schedule.pro.application.Entity.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.pro.application.Entity.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDto {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String email;
    private String experience;
    private String studies;
    private Role role;
    private int id;

}
