package mergefairy.gikhub.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.repository.UserRepository;
import mergefairy.gikhub.service.Dto.UserCreateDto;
import mergefairy.gikhub.service.Dto.UserEditDto;
import mergefairy.gikhub.service.Dto.UserInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl{
    private final UserRepository userRepository;

    public User createUser(UserCreateDto userCreateDto){
        return userRepository.save(userCreateDto.toEntity());
    };

    public void deleteUser(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 없습니다."));
        userRepository.delete(user);
    }

    public Map<String, String> validateHandler(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        // 유효성 검사에 실패한 필드 목록을 받음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public Boolean checkEmailDuplicate(String email) {
        if(userRepository.existsByEmail(email)){
            return true; //중복되는 경우 true
        }
        return false;
    }

    public Boolean checkNickNameDuplicate(String nickName) {
        if(userRepository.existsByNickName(nickName)){
            return true; //중복되는 경우 true
        }
        return false;
    }


    public UserInfoDto getMyInfo(String nickName){
        User findUser = userRepository.findByNickName(nickName).get();
                //.orElseThrow(()-> new IllegalArgumentException("정보를 가져올 수 없습니다."));
        return new UserInfoDto(findUser);
    }

    @Transactional
    public User update(UserEditDto userEditDto, String nickName) {
        User updateUser = userRepository.findByNickName(nickName).orElseThrow(()-> new IllegalArgumentException("수정할 정보가 없습니다."));

        //JPA 의 영속성 컨텍스트 덕분에 entity 객체의 값만 변경하면 자동으로 변경사항 반영
        updateUser.update(userEditDto.getPassword(), userEditDto.getNickName());
        return updateUser;
    }
}
