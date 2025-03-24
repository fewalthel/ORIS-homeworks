package ru.kpfu.orissemwork2.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rating")
/*@IdClass(RatingId.class)*/
public class Rating {
    @Id
    private Long id_user;

    @Id
    private Long id_answer;

    @Column(nullable = false)
    private Boolean isLiked;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "id_answer", nullable = false)
    private Answer answer;
}

