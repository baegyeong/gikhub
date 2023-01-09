package mergefairy.gikhub.repository;

import mergefairy.gikhub.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByAccountId(String accountId);

    Optional<User> findBynickName(String nickname);
}
