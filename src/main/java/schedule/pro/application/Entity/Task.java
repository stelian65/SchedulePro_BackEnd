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
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String dueDate;

    @Column
    private float totalHours;


    @Enumerated(EnumType.STRING)
    @Column(name="task_status")
    private TaskStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    private List<Clocking> clockings;


    public void addClocking(Clocking clocking){
        if(clockings == null){
            clockings = new ArrayList<>();
        }
        clockings.add(clocking);
    }
}
