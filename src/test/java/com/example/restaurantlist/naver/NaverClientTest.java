package com.example.restaurantlist.naver;

import com.example.restaurantlist.naver.dto.SearchImgReq;
import com.example.restaurantlist.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest(){
        var search = new SearchLocalReq();
        search.setQuery("갈비집");

        var result = naverClient.searchLocal(search);

        System.out.println(result);
        Assertions.assertNotNull(result.getItems().stream().findFirst().get().getCategory());
    }

    @Test
    public void searchImageTest(){
        var search = new SearchImgReq();
        search.setQuery("농구");

        var result = naverClient.searchImage(search);
        System.out.println(result);
    }
}
