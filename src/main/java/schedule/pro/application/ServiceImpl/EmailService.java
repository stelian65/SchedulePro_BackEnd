package schedule.pro.application.ServiceImpl;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to,String email,String password){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Schedule.Pro.Application");
        message.setTo(to);
        message.setText(
                "Hello,\n\n" +
                        "Your employer has registered you. We are excited to have you on board!\n\n" +
                        "Your account details are as follows:\n\n" +
                        "Username: " + email+ "\n" +
                        "Password: " + password + "\n\n" +
                        "We recommend you to change your password upon first login for security reasons.\n\n" +
                        "If you have any questions or need assistance, feel free to reach out to us.\n\n" +
                        "Best Regards,\n" +
                        "The SchedulePro Team"
        );
        message.setSubject("Welcome to SchedulePro");
        javaMailSender.send(message);
    }

    public void sendEmailChangePassword(String to, String password){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Schedule.Pro.Application");
        message.setTo(to);
        message.setText(
                "Hello,\n\n" +
                        "You have changed your password successfully!\n\n" +
                        "Your account details are as follows:\n\n" +
                        "Username: " + to+ "\n" +
                        "Password: " + password + "\n\n" +
                        "If you have any questions or need assistance, feel free to reach out to us.\n\n" +
                        "Best Regards,\n" +
                        "The SchedulePro Team"
        );
        message.setSubject("SchedulePro: Your password was changed");
        javaMailSender.send(message);
    }

}
