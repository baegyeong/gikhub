package mergefairy.gikhub.web.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.domain.login.LoginForm;
import mergefairy.gikhub.domain.login.LoginService;
import mergefairy.gikhub.web.session.SessionManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm")LoginForm form){
        return "/login/loginForm"; //로그인 시도 화면 F(x)
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response) {

        //로그인 에러 있을시 다시 로그인 시도 화면
        if (bindingResult.hasErrors()) return "/login/loginForm";

        User loginUser = loginService.login(form.getAccountId(), form.getPassword());
        log.info("login? {}", loginUser);

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/login";
        }

        // 로그인 성공 처리
        // 세션 관리자를 통해 세션 생성, 회원 데이터 보관
        sessionManager.createSession(loginUser, response);

        //로그인 성공 시 메인 화면으로 리다이렉트 F(x)
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        sessionManager.expireSession(request);
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response,String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        //즉시 종료
        cookie.setMaxAge(0);
        //null값인 쿠키로 덮기
        response.addCookie(cookie);
    }
}
