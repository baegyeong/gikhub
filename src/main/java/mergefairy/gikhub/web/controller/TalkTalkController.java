package mergefairy.gikhub.web.controller;

import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.service.Dto.TalkTalkCreateDto;
import mergefairy.gikhub.service.Dto.TalkTalkGetDto;
import mergefairy.gikhub.service.TalkTalkServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/talktalk")
public class TalkTalkController {
    private final TalkTalkServiceImpl talkTalkServiceImpl;

    //게시글 등록
    @PostMapping("/add")
    public ResponseEntity createTalkTalk(@Validated @RequestBody TalkTalkCreateDto talkTalkDto){
        TalkTalk createdTalkTalk = talkTalkServiceImpl.createTalkTalk(talkTalkDto);
        return new ResponseEntity(createdTalkTalk, HttpStatus.CREATED);
    }

    //게시글 삭제
    @DeleteMapping("/delete/{talkTalkNo}")
    public ResponseEntity deleteTalkTalk(@PathVariable Long talkTalkNo){
        talkTalkServiceImpl.deleteTalkTalk(talkTalkNo);
        return new ResponseEntity("{}", HttpStatus.OK);
    }

    //게시판
    //게시글 1개 조회
    @GetMapping("/{talkTalkNo}"
    )
    public ResponseEntity getOneTalkTalk(@Valid @PathVariable("talkTalkNo") Long no){
        TalkTalk findTalkTalk = talkTalkServiceImpl.getOneTalkTalk(no);
        return new ResponseEntity(findTalkTalk, HttpStatus.OK);
    }

    //게시글 목록 무한 스크롤으로 반환타입은 Slice
    //정렬순 : createdDate, commentCount
    @GetMapping("/list/{sortType}/{pageNumber}")
    public ResponseEntity<Slice<TalkTalkGetDto>> getTalkTalksSorted(@PathVariable int pageNumber, @PathVariable String sortType){
        Slice<TalkTalkGetDto> talkGetDtoSlice = talkTalkServiceImpl.getTalkTalksSorted(pageNumber, sortType);
        return new ResponseEntity(talkGetDtoSlice, HttpStatus.OK);
    }

//    //댓글순
//    @GetMapping("/list/commentCount/{pageNumber}")
//    public ResponseEntity<Slice<TalkTalkGetDto>> getTalkTalksSortedByCommentCount(@PathVariable int pageNumber) {
//        Slice<TalkTalkGetDto> talkTalksByCommentCount = talkTalkServiceImpl.getTalkTalksSortedByCommentCount(pageNumber);
//        return new ResponseEntity(talkTalksByCommentCount, HttpStatus.OK);
//    }
//
//    //최신순
//    @GetMapping("/list/new/{pageNumber}")
//    public ResponseEntity<Slice<TalkTalkGetDto>> getTalkTalksSortedByCreateAt(@PathVariable int pageNumber) {
//        Slice<TalkTalkGetDto> talkTalksByCreateAt = talkTalkServiceImpl.getTalkTalksSortedByCreateAt(pageNumber);
//        return new ResponseEntity(talkTalksByCreateAt, HttpStatus.OK);
//    }
}
