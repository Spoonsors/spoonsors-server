package com.spoonsors.spoonsorsserver.entity.bMember;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Role;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class BMemberSignUpDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String id;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$",
            message = "비밀번호는 8~15 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String pwd;

    private String pwd_check;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "생년월일을 입력해주세요.(예:20010904)")
    private String birth;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min=2, message = "2글자 이상으로 입력해주세요.")
    private String nickname;

    @NotBlank(message = "휴대전화 번호를 입력해주세요.")
    private String phoneNumber;

    private String address;

    private byte[] certificate;

    private String token;


    @Builder
    public BMember toEntity(){
        return BMember.builder()
                .bMember_id(id)
                .bMember_pwd(pwd)
                .bMember_name(name)
                .bMember_birth(birth)
                .bMember_nickname(nickname)
                .bMember_phoneNumber(phoneNumber)
                .bMember_address(address)
                .bMember_certificate(certificate)
                .role(Role.BMEMBER)
                .token(token)
                .is_verified(0)
                .build();
    }
}
