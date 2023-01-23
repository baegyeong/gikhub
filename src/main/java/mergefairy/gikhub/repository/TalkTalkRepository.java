package mergefairy.gikhub.repository;


import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.service.Dto.TalkTalkGetDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;


public interface TalkTalkRepository extends JpaRepository<TalkTalk, Long> {

    TalkTalk save(TalkTalk talkTalk);

    Optional<TalkTalk> findByTalkTalkNo(Long talkTalkNo);

    Slice<TalkTalk> findAllByOrderByCreatedDateDesc(Pageable pageable);

    Slice<TalkTalk> findAllByOrderByCommentCountDesc(Pageable pageable);

}
