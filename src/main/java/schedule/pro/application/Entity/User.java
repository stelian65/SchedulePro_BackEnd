package schedule.pro.application.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String experience;

    @Column
    private String studies;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name="user_role")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Task> tasks;
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Clocking> clockings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<InboxMessage> messages;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Request> requests;


    public void addTask(Task task){
        if(tasks == null){
           tasks = new ArrayList<>();
        }
        tasks.add(task);
    }

    public void addMessage(InboxMessage message) {
        if (message == null) {
            messages = new ArrayList<>();
        }
        messages.add(message);
    }

    public void addClocking(Clocking clocking){
        if(clockings == null){
            clockings = new ArrayList<>();
        }
        clockings.add(clocking);
    }

    public void addRequest(Request request){
        if(requests == null ){
            requests = new ArrayList<>();
        }
        requests.add(request);
    }
}
