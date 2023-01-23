package mergefairy.gikhub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class TalkTalk extends BaseTimeEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "talktalk_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    //글번호
    @Column(nullable = false)
    private int talkTalkNo;

    //작성자
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //댓글 갯수
    private int commentCount = 0;

    // 댓글 정렬
    @OneToMany(mappedBy = "talkTalk", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comment;

    //게시글 수정 코드
    //앱에 추가할지 미정
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    //댓글 갯수 추가, 삭제 메소드
    public void addComment(){
        this.commentCount++;
    }

    public void deleteComment(){
        this.commentCount--;
    }
}
