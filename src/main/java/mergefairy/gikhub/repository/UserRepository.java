package mergefairy.gikhub.repository;

import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByNickName(String nickname);

    User save(User user);

    boolean existsByEmail(String email);

    boolean existsByNickName(String nickName);
}
