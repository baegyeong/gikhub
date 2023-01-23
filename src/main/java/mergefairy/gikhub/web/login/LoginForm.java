package mergefairy.gikhub.web.login;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginForm {
    public String email;
    public String password;
}
