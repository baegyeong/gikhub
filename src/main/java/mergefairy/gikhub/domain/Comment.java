package mergefairy.gikhub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;


@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "talktalk_id")
    private TalkTalk talkTalk;

    //댓글 작성자
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
