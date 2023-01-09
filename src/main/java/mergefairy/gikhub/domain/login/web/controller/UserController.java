package mergefairy.gikhub.web.controller;

import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.service.UserCreateDto;
import mergefairy.gikhub.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    /*회원가입 폼
    @GetMapping("/add")
    public String createUserForm(@ModelAttribute("User") User user)
    */


    //회원가입
    @PostMapping("/add")
    public ResponseEntity<User> createUser(UserCreateDto userCreateDto){
        User user = userServiceImpl.createUser(userCreateDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

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
