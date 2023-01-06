package mergefairy.gikhub.service;

import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User createUser(UserCreateDto userCreateDto){
        return userRepository.save(userCreateDto.toEntity());
    };

    public void deleteUser(String accountId){
        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 없습니다."));
        userRepository.delete(user);
    }
}
