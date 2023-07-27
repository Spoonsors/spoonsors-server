package com.spoonsors.spoonsorsserver.service.bMember;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Fridge;
import com.spoonsors.spoonsorsserver.entity.bMember.FridgeDto;
import com.spoonsors.spoonsorsserver.repository.BMemberRepository;
import com.spoonsors.spoonsorsserver.repository.FridgeRepository;
import com.spoonsors.spoonsorsserver.repository.IFridgeRepository;
import com.spoonsors.spoonsorsserver.repository.IbMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FridgeService {

    private final FridgeRepository fridgeRepository;
    private final BMemberRepository bMemberRepository;
    private final IbMemberRepository ibMemberRepository;
    private final IFridgeRepository iFridgeRepository;

    public Fridge addFridgeItem(String bMemberId, FridgeDto fridgeDto){
        Optional<BMember> optionalBMember =ibMemberRepository.findById(bMemberId);
        BMember bMember = optionalBMember.get();
        Fridge addFridgeItem=iFridgeRepository.save(fridgeDto.toEntity());
        addFridgeItem.setBMember(bMember);
        return addFridgeItem;
    }

    public List<Fridge> getFridgeDto(String bMemberId){
        List<Fridge> fridgeItems = fridgeRepository.getFridgeItems(bMemberId);
        return fridgeItems;
    }

    public void removeFridge(Long fridge_id){
        fridgeRepository.deleteFridgeItem(fridge_id);
    }

}
