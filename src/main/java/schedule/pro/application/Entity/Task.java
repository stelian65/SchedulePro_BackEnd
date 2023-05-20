package schedule.pro.application.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
}
