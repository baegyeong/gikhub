package mergefairy.gikhub.service.Dto;

import lombok.*;
import mergefairy.gikhub.domain.User;

@Data
@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TalkTalkGetDto {

    private String title;
    //private int talkTalkNo;
    private User user;
    private int commentCount;

//    public TalkTalkGetDto fromLists(TalkTalk talkTalk) {
//        return TalkTalkGetDto.builder()
//                .title(talkTalk.getTitle())
//                .user(talkTalk.getUser())
//                .commentCount(talkTalk.getCommentCount())
//                .build();
//    }
}
