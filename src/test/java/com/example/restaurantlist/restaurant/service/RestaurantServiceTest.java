package com.example.restaurantlist.restaurant.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestaurantServiceTest {

    @Autowired
    private RestaurantService restaurantService;

    @Test
    public void searchTest(){
        var result = restaurantService.search("스프링");
        System.out.println(result);
        Assertions.assertNotNull(result);
    }

}
