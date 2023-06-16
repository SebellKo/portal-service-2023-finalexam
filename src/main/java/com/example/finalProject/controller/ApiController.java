package com.example.finalProject.controller;

import com.example.finalProject.wishlist.dto.WishListDto;
import com.example.finalProject.wishlist.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // @ResponseBody + @Controller JSON/XML형태로 객체 데이터를 주고받음을 선언.
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor // 클래스의 필수 인자를 가지는 생성자를 자동으로 생성.
public class ApiController {

    private final WishListService wishListService;
    @GetMapping("/search")
    public WishListDto search(@RequestParam String query) {
        return wishListService.search(query);
    }

    @PostMapping("")
    public WishListDto add(@RequestBody WishListDto wishListDto) {
        log.info("{}", wishListDto);

        return wishListService.add(wishListDto);
    }
    @GetMapping("/all")
    public List<WishListDto> findAll() {
        return wishListService.findAll();
    }
    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index) {
        wishListService.delete(index);
    } // url 에서 index를 추출하기 위해 @PathVariable 사용

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index) {
        wishListService.addVisit(index);

    }
}
