package com.spoonsors.spoonsorsserver.entity.sMember;

import com.spoonsors.spoonsorsserver.entity.Role;
import com.spoonsors.spoonsorsserver.entity.SMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
public class SMemberSignUpDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String sMember_id;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$",
            message = "비밀번호는 8~15 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String sMember_pwd;

    private String sMember_pwd_check;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min=2, message = "2글자 이상으로 입력해주세요.")
    private String sMember_nickname;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String sMember_phoneNumber;

    private Role role;

    @Builder
    public SMember toEntity(){
        return SMember.builder()
                .sMember_id(sMember_id)
                .sMember_pwd(sMember_pwd)
                .sMember_nickname(sMember_nickname)
                .sMember_phoneNumber(sMember_phoneNumber)
                .role(Role.SMEMBER)
                .build();
    }
}
