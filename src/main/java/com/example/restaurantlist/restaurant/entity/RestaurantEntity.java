package com.example.restaurantlist.restaurant.entity;

import com.example.restaurantlist.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity extends MemoryDbEntity {

    private String title;                   // 음식명, 장소명
    private String category;                // 카테고리
    private String address;                 // 주소
   private String readAddress;              // 도로명
    private String homePageLink;            // 홈페이지 주소
    private String imageLink;               // 음식, 가게 이미지 주소
    private boolean isVisit;                // 방문여부
    private int visitCount;                 // 방문 횟수
    private LocalDateTime lastVisitDate;    // 마지막 방문일자

}