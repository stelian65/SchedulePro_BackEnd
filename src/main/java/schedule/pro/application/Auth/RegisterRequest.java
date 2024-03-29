package schedule.pro.application.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import schedule.pro.application.Entity.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String password;
    private String experience;
    private String studies;
}