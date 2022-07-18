package com.example.restaurantlist.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto{ // DB의 entity가 변경되었을 떄 front-end의 변수명까지도 변경해야되는 요소가 생길 수 있기 때문에
    // 중간에 변환만 시켜주기 위해 분리해서 하나 더 만듬
    private Integer index;
    private String title;                   // 음식명, 장소명
    private String category;                // 카테고리
    private String address;                 // 주소
   private String roadAddress;              // 도로명
    private String homePageLink;            // 홈페이지 주소
    private String imageLink;               // 음식, 가게 이미지 주소
    private boolean isVisit;                // 방문여부
    private int visitCount;                 // 방문 횟수
    private LocalDateTime lastVisitDate;    // 마지막 방문일자

}
