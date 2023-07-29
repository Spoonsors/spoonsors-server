package com.spoonsors.spoonsorsserver.controller.bMember;

import com.spoonsors.spoonsorsserver.entity.Fridge;
import com.spoonsors.spoonsorsserver.entity.bMember.FridgeDto;
import com.spoonsors.spoonsorsserver.service.bMember.FridgeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FridgeController {

    private final FridgeService fridgeService;

    //아이템 추가
    @PostMapping(value = "bMember/fridge/add/{bMemberId}", consumes = {MediaType.APPLICATION_JSON_VALUE, "multipart/form-data"})
    public Fridge addFridgeItem(@PathVariable String bMemberId, @RequestPart FridgeDto fridgeDto, @RequestPart(value = "img", required = false) MultipartFile img){
        log.info("멤버 아이디: {}", bMemberId);
        Fridge addedItem= null;
        try {
            addedItem = fridgeService.addFridgeItem(bMemberId, fridgeDto, img);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @GetMapping("/showFridgeImage/{fridge_id}")
    public ResponseEntity<?> showFridgeImage(@PathVariable Long fridge_id) {
        byte[] downloadImage = fridgeService.downloadImage(fridge_id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

}
