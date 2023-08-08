package com.spoonsors.spoonsorsserver.service.member;

import com.spoonsors.spoonsorsserver.repository.BMemberRepository;
import com.spoonsors.spoonsorsserver.repository.ISMemberRepository;
import com.spoonsors.spoonsorsserver.repository.IbMemberRepository;
import com.spoonsors.spoonsorsserver.repository.SMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class JoinService {
    private final IbMemberRepository ibMemberRepository;
    private final ISMemberRepository isMemberRepository;
    private final BMemberRepository bMemberRepository;
    private final SMemberRepository sMemberRepository;
    public String checkId(String id) {
        if (ibMemberRepository.findById(id).isPresent()){
            return "이미 존재하는 아이디입니다.";
        }
        if (isMemberRepository.findById(id).isPresent()){
            return "이미 존재하는 아이디입니다.";
        }

        return "사용 가능한 아이디입니다.";
    }

    public String checkNickname(String nickname) {

        if (!bMemberRepository.findByNickname(nickname).isEmpty()){
            return "이미 존재하는 닉네임입니다.";
        }
        if (!sMemberRepository.findByNickname(nickname).isEmpty()){
            return "이미 존재하는 닉네임입니다.";
        }
        return "사용 가능한 닉네임입니다.";
    }
}
