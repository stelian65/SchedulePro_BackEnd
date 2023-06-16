package schedule.pro.application.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="request_type")
    private RequestType type;

    @Column(name="`from`")
    private String from ;
    @Column(name="`to`")
    private String to ;

    @Column
    private boolean pending;

    @Column
    private boolean response;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
