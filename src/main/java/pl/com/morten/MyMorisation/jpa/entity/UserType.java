package pl.com.morten.MyMorisation.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "userType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name =  "username")
    private Long userId;

    @Column(name = "userType")
    private String userType;
}
