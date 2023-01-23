package mergefairy.gikhub.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.web.login.LoginForm;
import mergefairy.gikhub.web.login.LoginService;
import mergefairy.gikhub.web.session.SessionConst;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {

        User loginUser = loginService.login(form.getEmail(), form.getPassword());
        log.info("login? {}", loginUser);

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
        }

        // 로그인 성공 처리

        // 세션 있으면 세션 반환, 없으면 신규 세션 생성
        //getSession()의 디폴트 값은 true -> 세션이 없으면 새로운 세션 생성 후 반환
        HttpSession session = request.getSession();
        // 세션에 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return new ResponseEntity(loginUser, HttpStatus.OK);
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request){

        //세션 삭제
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();

        return new ResponseEntity("로그아웃 성공", HttpStatus.OK);
    }
}
