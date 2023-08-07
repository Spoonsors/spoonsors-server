package com.spoonsors.spoonsorsserver.service.bMember;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.bMember.BMemberSignUpDto;
import com.spoonsors.spoonsorsserver.repository.BMemberRepository;
import com.spoonsors.spoonsorsserver.repository.ISMemberRepository;
import com.spoonsors.spoonsorsserver.repository.IbMemberRepository;
import com.spoonsors.spoonsorsserver.repository.SMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BMemberService {
    private final ISMemberRepository isMemberRepository;
    private final BMemberRepository bMemberRepository;
    private final IbMemberRepository ibMemberRepository;
    private final SMemberRepository sMemberRepository;
    private final PasswordEncoder passwordEncoder;

    public String signUp(BMemberSignUpDto requestDto) throws Exception {

        if (ibMemberRepository.findByEmail(requestDto.getBMember_id()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (isMemberRepository.findByEmail(requestDto.getBMember_id()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (bMemberRepository.findByNickname(requestDto.getBMember_nickname()).isEmpty()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if (sMemberRepository.findByNickname(requestDto.getBMember_nickname()).isEmpty()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if (!requestDto.getBMember_pwd().equals(requestDto.getBMember_pwd_check())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        BMember member = ibMemberRepository.save(requestDto.toEntity());
        member.encodePassword(passwordEncoder);

        //member.addUserAuthority();
        return member.getBMember_id();
    }
}
