package com.spoonsors.spoonsorsserver.service.sMember;

import com.spoonsors.spoonsorsserver.entity.SMember;
import com.spoonsors.spoonsorsserver.entity.sMember.SMemberSignUpDto;
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
public class SMemberService {

    private final ISMemberRepository isMemberRepository;
    private final PasswordEncoder passwordEncoder;
    private final IbMemberRepository ibMemberRepository;
    private final BMemberRepository bMemberRepository;
    private final SMemberRepository sMemberRepository;


    public String signUp(SMemberSignUpDto requestDto) throws Exception {

        if (isMemberRepository.findByEmail(requestDto.getSMember_id()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (ibMemberRepository.findByEmail(requestDto.getSMember_id()).isPresent()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        if (bMemberRepository.findByNickname(requestDto.getSMember_nickname()).isEmpty()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if (sMemberRepository.findByNickname(requestDto.getSMember_nickname()).isEmpty()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if (!requestDto.getSMember_pwd().equals(requestDto.getSMember_pwd_check())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        SMember member = isMemberRepository.save(requestDto.toEntity());
        member.encodePassword(passwordEncoder);

        //member.addUserAuthority();
        return member.getSMember_id();
    }
}
