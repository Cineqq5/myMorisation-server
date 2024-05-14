package pl.com.morten.MyMorisation.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cards")
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name =  "userId")
    private Long userId;

    @Column(name =  "originalWord")
    private String originalWord;

    @Column(name = "translatedWord")
    private String translatedWord;

    @Column(name = "progress")
    private int progress;

    @Column(name = "timeOfNextReview")
    private Date timeOfNextReview;
}
