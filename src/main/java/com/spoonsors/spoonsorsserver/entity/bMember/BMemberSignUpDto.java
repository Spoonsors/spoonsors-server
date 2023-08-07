package com.spoonsors.spoonsorsserver.entity.bMember;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class BMemberSignUpDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String bMember_id;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,15}$",
            message = "비밀번호는 8~15 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
    private String bMember_pwd;

    private String bMember_pwd_check;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min=2, message = "2글자 이상으로 입력해주세요.")
    private String bMember_nickname;

    @NotBlank(message = "휴대전화 번호를 입력해주세요.")
    private String bMember_phoneNumber;

    private String bMember_address;

    private byte[] bMember_certificate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public BMember toEntity(){
        return BMember.builder()
                .bMember_id(bMember_id)
                .bMember_pwd(bMember_pwd)
                .bMember_nickname(bMember_nickname)
                .bMember_phoneNumber(bMember_phoneNumber)
                .bMember_address(bMember_address)
                .bMember_certificate(bMember_certificate)
                .role(Role.BMEMBER)
                .build();
    }
}
