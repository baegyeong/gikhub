package mergefairy.gikhub.web.controller;

import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.service.UserCreateDto;
import mergefairy.gikhub.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class userController {
    private final UserServiceImpl userServiceImpl;

    /*
    post
    회원가입
     */

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(UserCreateDto userCreateDto){
        User user = userServiceImpl.createUser(userCreateDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@GetMapping("/api/users")
    /*
    get
    회원 조회
     */

    /*
    pacth
    회원 수정
     */

    /*
    Delete
    회원 삭제
     */
}
