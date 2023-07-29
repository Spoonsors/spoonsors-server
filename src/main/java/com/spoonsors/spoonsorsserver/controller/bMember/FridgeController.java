package com.spoonsors.spoonsorsserver.controller.bMember;

import com.spoonsors.spoonsorsserver.entity.Fridge;
import com.spoonsors.spoonsorsserver.entity.bMember.FridgeDto;
import com.spoonsors.spoonsorsserver.service.bMember.FridgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FridgeController {

    private final FridgeService fridgeService;

    //아이템 추가
    @PostMapping("bMember/fridge/add/{bMemberId}")
    public Fridge addFridgeItem(@PathVariable String bMemberId, @RequestBody FridgeDto fridgeDto){
        Fridge addedItem=fridgeService.addFridgeItem(bMemberId, fridgeDto);
        return addedItem;
    }

    //냉장고 조회
    @GetMapping("/bMember/fridge/{bMemberId}")
    public List<Fridge> getFridge(@PathVariable String bMemberId){
        List<Fridge> fridgeItems = fridgeService.getFridgeDto(bMemberId);
        return fridgeItems;
    }

    //냉장고 아이템 삭제
    @DeleteMapping("/bMember/fridge/delete/{fridge_id}")
    public String deleteFridge(@PathVariable Long fridge_id){
        fridgeService.removeFridge(fridge_id);
        return "삭제가 완료되었습니다.";
    }
}
