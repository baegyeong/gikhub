package mergefairy.gikhub.service.Dto;

import lombok.Data;
import mergefairy.gikhub.domain.User;

@Data
public class UserInfoDto {
    private String email;
    private String realName;
    private String password;
    private String phoneNo;
    private String nickName;

    public UserInfoDto(User user){
        this.email = user.getEmail();
        this.realName = user.getRealName();
        this.phoneNo = user.getPhoneNo();
        this.password = user.getPassword();
        this.nickName = user.getNickName();
    }

}
