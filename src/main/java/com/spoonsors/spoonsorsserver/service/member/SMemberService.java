package com.spoonsors.spoonsorsserver.service.member;

import com.spoonsors.spoonsorsserver.entity.SMember;
import com.spoonsors.spoonsorsserver.entity.sMember.SMemberSignUpDto;
import com.spoonsors.spoonsorsserver.loginInfra.JwtTokenProvider;
import com.spoonsors.spoonsorsserver.repository.BMemberRepository;
import com.spoonsors.spoonsorsserver.repository.ISMemberRepository;
import com.spoonsors.spoonsorsserver.repository.IbMemberRepository;
import com.spoonsors.spoonsorsserver.repository.SMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class SMemberService {

    private final ISMemberRepository isMemberRepository;
    private final IbMemberRepository ibMemberRepository;
    private final BMemberRepository bMemberRepository;
    private final SMemberRepository sMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder encoder;
    public String signUp(SMemberSignUpDto requestDto) throws Exception {

        if (isMemberRepository.findById(requestDto.getId()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (ibMemberRepository.findById(requestDto.getId()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (bMemberRepository.findByNickname(requestDto.getNickname()).isPresent()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if (sMemberRepository.findByNickname(requestDto.getNickname()).isPresent()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if (!requestDto.getPwd().equals(requestDto.getPwd_check())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        SMember member = isMemberRepository.save(requestDto.toEntity());
        member.encodePassword(passwordEncoder);

        //member.addUserAuthority();
        return member.getSMember_id();
    }

    public String login(Map<String, String> members) {

        //BMember bMember = ibMemberRepository.findByEmail(members.get("Id"))
        //         .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Id 입니다."));

        //String password = members.get("password");
        //if (!bMember.(passwordEncoder, password)) {
        //    throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        // }
        SMember sMember = isMemberRepository.findById(members.get("Id"))
                .filter(it -> encoder.matches(members.get("password"), it.getSMember_pwd()))   // 암호화된 비밀번호와 비교하도록 수정
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));


        List<String> roles = new ArrayList<>();
        roles.add(sMember.getRole().name());

        return jwtTokenProvider.createToken(sMember.getSMember_id(), roles);
    }
}
