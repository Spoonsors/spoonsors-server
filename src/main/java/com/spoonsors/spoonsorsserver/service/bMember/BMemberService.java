package com.spoonsors.spoonsorsserver.service.bMember;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.bMember.BMemberSignUpDto;
import com.spoonsors.spoonsorsserver.loginInfra.JwtTokenProvider;
import com.spoonsors.spoonsorsserver.repository.IbMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service

public class BMemberService {

    private final IbMemberRepository ibMemberRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder encoder;
    public String signUp(BMemberSignUpDto requestDto) throws Exception {

        if (ibMemberRepository.findById(requestDto.getBMember_id()).isPresent()) {
            throw new Exception("이미 존재하는 아이디입니다.");
        }

        if (!requestDto.getBMember_pwd().equals(requestDto.getBMember_pwd_check())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        BMember member = ibMemberRepository.save(requestDto.toEntity());
        member.encodePassword(passwordEncoder);

        //member.addUserAuthority();
        return member.getBMember_id();
    }


    public String login(Map<String, String> members) {

       //BMember bMember = ibMemberRepository.findByEmail(members.get("Id"))
       //         .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Id 입니다."));

        //String password = members.get("password");
        //if (!bMember.(passwordEncoder, password)) {
        //    throw new IllegalArgumentException("잘못된 비밀번호입니다.");
       // }
        BMember bMember = ibMemberRepository.findById(members.get("Id"))
                .filter(it -> encoder.matches(members.get("password"), it.getBMember_pwd()))   // 암호화된 비밀번호와 비교하도록 수정
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));


        List<String> roles = new ArrayList<>();
        roles.add(bMember.getRole().name());

        return jwtTokenProvider.createToken(bMember.getBMember_id(), roles);
    }
}
