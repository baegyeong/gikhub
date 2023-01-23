package mergefairy.gikhub.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TogetherTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    private String storeName;
    private LocalDateTime teamDeadLine;

    //배송받을 기숙사
    private String receive;

    //최대 인원
    private int maxUser;

    //방장
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
