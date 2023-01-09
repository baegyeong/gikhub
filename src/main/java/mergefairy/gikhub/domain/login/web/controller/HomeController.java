package mergefairy.gikhub.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.repository.UserRepository;
import mergefairy.gikhub.web.session.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserRepository userRepository;
    private final SessionManager sessionManager;

    //홈 (Fx)
    public String home() {
        return "home";
    }

    //로그인 하지 않은 사용자도 홈에 접근할 수 있기 때문에 required = false 를 사용
    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model){

        //세션 관리자에 저장된 회원 정보 조회
        //null이면 쿠키나 세션이 없는 것 -> 로그인 X
        User user = (User) sessionManager.getSession(request);
        if(user == null) return "home";

        //로그인
        if(user == null) return "home";

        //로그인
        model.addAttribute("user", user);
        return "loginUser";
    }
}
