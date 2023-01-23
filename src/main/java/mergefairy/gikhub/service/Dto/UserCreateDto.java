package mergefairy.gikhub.service.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import mergefairy.gikhub.domain.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Email(message = "유효하지 않는 이메일 형식입니다.")
    private String email;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank
    private String realName;

    private String phoneNo;
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,6}$", message = "닉네임은 특수문자를 제외한 2~6자리여야 합니다.")
    private String nickName;

    public User toEntity(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .phoneNo(this.phoneNo)
                .nickName(this.nickName)
                .realName(this.realName)
                .build();
    }

}
