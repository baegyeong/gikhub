package mergefairy.gikhub.service;

import lombok.Getter;
import lombok.Setter;
import mergefairy.gikhub.domain.User;

@Getter
public class UserCreateDto {
    private String accountId;

    public User toEntity(){
        return User.builder().build();
    }

}
