package com.spoonsors.spoonsorsserver.controller.bMember;

import com.spoonsors.spoonsorsserver.entity.Fridge;
import com.spoonsors.spoonsorsserver.entity.bMember.GetFridgeDto;
import com.spoonsors.spoonsorsserver.service.bMember.FridgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FridgeController {

    private final FridgeService fridgeService;

    //냉장고 조회
    @GetMapping("/bMember/fridge/{bMemberId}")
    public List<Fridge> getFridge(@PathVariable String bMemberId){
        List<Fridge> fridgeItems = fridgeService.getFridgeDto(bMemberId);
        return fridgeItems;
    }

}
