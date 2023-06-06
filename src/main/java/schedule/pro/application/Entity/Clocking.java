package schedule.pro.application.Entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Clocking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column
    public int day;

    @Column
    public int month;

    @Column
    public int year;

    @Column
    public float hours;

    @ManyToOne
    @JoinColumn(name = "task_id")
    public Task task;

    @ManyToOne
    @JoinColumn(name="user_Id")
    public User user;
}
