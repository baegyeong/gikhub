package mergefairy.gikhub.service;

import lombok.Getter;
import lombok.Setter;
import mergefairy.gikhub.domain.User;

@Getter
public class UserCreateDto {
    private String accountId;
    private String email;
    private String password;
    private String phoneNo;
    private String nickName;

    public User toEntity(){
        return User.builder()
                .accountId(this.accountId)
                .email(this.email)
                .password(this.password)
                .phoneNo(this.phoneNo)
                .nickName(this.nickName)
                .build();
    }

}
