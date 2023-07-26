package com.spoonsors.spoonsorsserver.service.bMember;

import com.spoonsors.spoonsorsserver.entity.Fridge;
import com.spoonsors.spoonsorsserver.entity.bMember.GetFridgeDto;
import com.spoonsors.spoonsorsserver.repository.FridgeRepository;
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

    public List<Fridge> getFridgeDto(String bMemberId){
        List<Fridge> fridgeItems = fridgeRepository.getFridgeItems(bMemberId);
        return fridgeItems;
    }
}
