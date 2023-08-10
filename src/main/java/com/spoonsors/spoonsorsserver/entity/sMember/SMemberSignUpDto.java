package com.spoonsors.spoonsorsserver.entity.sMember;

import com.spoonsors.spoonsorsserver.entity.Role;
import com.spoonsors.spoonsorsserver.entity.SMember;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
public class SMemberSignUpDto {

    private String id;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$",
            message = "비밀번호는 8~15 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String pwd;

    private String pwd_check;

    @Size(min=2, message = "2글자 이상으로 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    private String phoneNumber;


    @Builder
    public SMember toEntity(){
        return SMember.builder()
                .sMember_id(id)
                .sMember_pwd(pwd)
                .sMember_name(name)
                .sMember_nickname(nickname)
                .sMember_phoneNumber(phoneNumber)
                .role(Role.SMEMBER)
                .build();
    }
}
