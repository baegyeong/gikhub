package mergefairy.gikhub.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.service.Dto.UserCreateDto;
import mergefairy.gikhub.service.Dto.UserEditDto;
import mergefairy.gikhub.service.Dto.UserInfoDto;
import mergefairy.gikhub.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserServiceImpl userServiceImpl;

    //회원가입
    @PostMapping("/validation/join")
    public ResponseEntity joinUser(@Validated @RequestBody UserCreateDto userCreateDto, Errors errors, Model model) {
        System.out.println("사용자 이름" + userCreateDto.getNickName());
        /* post요청시 넘어온 user 입력값에서 Validation에 걸리는 경우 */
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 유지 */
            model.addAttribute("userCreateDto", userCreateDto);
            /* 회원가입 실패시 message 값들을 모델에 매핑해서 View로 전달 */
            Map<String, String> validateResult = userServiceImpl.validateHandler(errors);
            // map.keySet() -> 모든 key값을 갖고온다.
            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
            for (String key : validateResult.keySet()) {
                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
                model.addAttribute(key, validateResult.get(key));
            }
            log.info("join 실패");
            //
        }

        User createdUser = userServiceImpl.createUser(userCreateDto);
        log.info("join 성공");

        return new ResponseEntity(createdUser, HttpStatus.CREATED);
    }

    //회원가입 시 이메일 중복 확인
    @GetMapping("/{email}/exists") //  localhost:8080/api/user/{email}/exists
    public ResponseEntity<Boolean> checkEmailDuplicacte(@PathVariable String email){
        log.info(email);
        //중복되는 경우 true 리턴
        return ResponseEntity.ok(userServiceImpl.checkEmailDuplicate(email));
    }

    //회원가입 시 닉네임 중복 확인
    @GetMapping("/{nickName}/exists")
    public ResponseEntity<Boolean> checkNickNameDuplicacte(@PathVariable String nickName){
        //중복되는 경우 true 리턴
        return ResponseEntity.ok(userServiceImpl.checkNickNameDuplicate(nickName));
    }

    //마이페이지의 내정보 보기
    @GetMapping("/{nikcName}")
    public ResponseEntity getMyInfo(@Valid @PathVariable("nickName") String nickName) throws Exception{
        UserInfoDto userInfoDto = userServiceImpl.getMyInfo(nickName);
        return new ResponseEntity(userInfoDto,HttpStatus.OK);
    }

    //회원 정보 수정
    @PutMapping("/{nickName}")
    public ResponseEntity updateMyInfo(@PathVariable("nickName") String nickName, UserEditDto userEditDto){
        User updatedUser = userServiceImpl.update(userEditDto, nickName);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    //회원 삭제(탈퇴)
    @DeleteMapping("/{nickName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@Validated @PathVariable String nickName){
        userServiceImpl.deleteUser(nickName);
    }
}
