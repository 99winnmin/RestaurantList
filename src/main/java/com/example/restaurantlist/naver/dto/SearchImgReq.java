package com.example.restaurantlist.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImgReq {

    private String query = "";

    private int display = 10;

    private int start = 1;

    private String sort = "sim";

    private String filter = "all";

    // MultiValueMap : key, value가 들어가는 형태
   public MultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();

        map.add("query",query);
        map.add("display",String.valueOf(display));
        map.add("start",String.valueOf(start));
        map.add("sort",sort);
        map.add("filter",filter);
        return map;
    }
}
