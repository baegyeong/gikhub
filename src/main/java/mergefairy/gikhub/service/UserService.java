package mergefairy.gikhub.service;


import mergefairy.gikhub.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User createUser(UserCreateDto userCreateDto);
    public void deleteUser(String accountId);
}
