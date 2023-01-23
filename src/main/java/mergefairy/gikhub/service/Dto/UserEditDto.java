package mergefairy.gikhub.service.Dto;

import lombok.Data;
import mergefairy.gikhub.domain.User;

@Data
public class UserEditDto {
    private String password;
    private String nickName;

    public UserEditDto(User user){
        this.password = user.getPassword();
        this.nickName = user.getNickName();
    }
}
