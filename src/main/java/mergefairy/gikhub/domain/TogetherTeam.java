package mergefairy.gikhub.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TogetherTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(nullable = false, name = "room_type")
    private boolean roomType;
    //방 타입으로 0이면 같이 사요, 1이면 같이 먹어요

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
