package mergefairy.gikhub.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.repository.TalkTalkRepository;
import mergefairy.gikhub.service.Dto.TalkTalkCreateDto;
import mergefairy.gikhub.service.Dto.TalkTalkGetDto;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TalkTalkServiceImpl{

    private final TalkTalkRepository talkTalkRepository;

    //게시글 생성
    public TalkTalk createTalkTalk(TalkTalkCreateDto talkTalkDto){
        return talkTalkRepository.save(talkTalkDto.toEntity());
    }

    //게시글 1개 조회
    public TalkTalk getOneTalkTalk(Long talkTalkNo){
        return talkTalkRepository.findByTalkTalkNo(talkTalkNo).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 삭제되었거나 존재하지 않습니다."));
    }

    //게시글 목록 조회
    //정렬순 : createdDate, commentCount
    public Slice<TalkTalkGetDto> getTalkTalksSorted(int pageNumber, String sortType){
        Pageable pageable = PageRequest.of(pageNumber, 7, Sort.by(Sort.Direction.DESC, sortType));
        Slice<TalkTalk> createdSlice = talkTalkRepository.findAllByOrderByCreatedDateDesc(pageable);
        List<TalkTalkGetDto> createDtoList = createdSlice.getContent()
                .stream()
                .map(talkTalk -> convertToDto(talkTalk))
                .collect(Collectors.toList());
        return new SliceImpl<>(createDtoList, pageable, createdSlice.hasNext());
    }

//    //최신순
//    public Slice<TalkTalkGetDto> getTalkTalksSortedByCreateAt(int pageNumber){
//        Pageable pageable = PageRequest.of(pageNumber, 7, Sort.by(Sort.Direction.DESC, "createdDate"));
//        Slice<TalkTalk> createdSlice = talkTalkRepository.findAllByOrderByCreatedDateDesc(pageable);
//        List<TalkTalkGetDto> createDtoList = createdSlice.getContent()
//                .stream()
//                .map(talkTalk -> convertToDto(talkTalk))
//                .collect(Collectors.toList());
//        return new SliceImpl<>(createDtoList, pageable, createdSlice.hasNext());
//    }
//
//    //댓글순
//    public Slice<TalkTalkGetDto> getTalkTalksSortedByCommentCount(int pageNumber){
//        Pageable pageable = PageRequest.of(pageNumber, 7, Sort.by(Sort.Direction.DESC, "commentCount"));
//        Slice<TalkTalk> talkTalkSlice= talkTalkRepository.findAllByOrderByCommentCountDesc(pageable);
//        List<TalkTalkGetDto> talkTalkGetDtoList = talkTalkSlice.getContent()
//                .stream()
//                .map(talkTalk -> convertToDto(talkTalk))
//                .collect(Collectors.toList());
//        return new SliceImpl<>(talkTalkGetDtoList, pageable, talkTalkSlice.hasNext());
//    }

    private TalkTalkGetDto convertToDto(TalkTalk talkTalk){
        return new TalkTalkGetDto(talkTalk.getTitle(), talkTalk.getUser().toString(), talkTalk.getCommentCount());
    }

    //게시글 수정
    @Transactional
    public void updateTalkTalk(Long talkTalkNo, String title, String content){
        TalkTalk findTalkTalk = talkTalkRepository.findByTalkTalkNo(talkTalkNo).orElseThrow(()-> new IllegalArgumentException("해당하는 글이 없습니다."));
        findTalkTalk.update(title, content);
    }

    //게시글 삭제
    @Transactional
    public void deleteTalkTalk(Long talkTalkNo){
        TalkTalk deleteTalkTalk = talkTalkRepository.findByTalkTalkNo(talkTalkNo).orElseThrow(() -> new IllegalArgumentException("해당하는 글이 없습니다."));
        talkTalkRepository.delete(deleteTalkTalk);
    }
}
