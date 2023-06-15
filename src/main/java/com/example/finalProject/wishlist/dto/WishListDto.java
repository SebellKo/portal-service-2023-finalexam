package com.example.finalProject.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WishListDto { //database의 엔티티 변경시 프론트에도 변수명을 변경해야하는 영향을 끼치기 때문에 별도로 분리
    private Integer index;
    private String title; // 음식명, 장소명
    private String category; // 카테고리
    private String address; // 주소
    private String roadAddress; // 도로명
    private String homePageLink; // 홈페이지 주소
    private String imageLink; // 음식, 가게 이미지 주소
    private boolean isVisit; // 방문여부
    private int visitCount; // 방문카운트
    private LocalDateTime lastVisitDate; // 마지막 방문 날짜

}
