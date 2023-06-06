package schedule.pro.application.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public String name;

    @Column
    public String description;

    @Column
    public Timestamp dueDate;

    @Column
    public float totalHours;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL)
    public List<Clocking> clockings;


    public void addClocking(Clocking clocking){
        if(clockings == null){
            clockings = new ArrayList<>();
        }
        clockings.add(clocking);
    }
}
