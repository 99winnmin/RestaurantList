package com.example.restaurantlist.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImgRes {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private String category;
    private List<SearchLocalItem> items;

    // api 에서 한 변수가 여러가지 데이터를 담고 있을 때는 static class로 하나 만들어준다.
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchLocalItem{
        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;
    }
}
