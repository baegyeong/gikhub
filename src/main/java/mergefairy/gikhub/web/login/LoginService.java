package mergefairy.gikhub.web.login;

import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;

    //@return null이면 로그인 실패
    public User login(String email, String password){
        return userRepository.findByEmail(email)
                .filter(u ->u.getPassword().equals(password))
                .orElse(null);
    }
}
