package com.example.restaurantlist.restaurant.repository;

import com.example.restaurantlist.restaurant.entity.RestaurantEntity;
import com.example.restaurantlist.restaurant.repositroy.RestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    private RestaurantEntity create(){
        var restaurant = new RestaurantEntity();
        restaurant.setTitle("title");
        restaurant.setCategory("category");
        restaurant.setAddress("address");
        restaurant.setRoadAddress("readAddress");
        restaurant.setHomePageLink("homePageLink");
        restaurant.setImageLink("imageLink");
        restaurant.setVisit(false);
        restaurant.setVisitCount(0);
        restaurant.setLastVisitDate(null);

        return restaurant;
    }

    @Test
    public void saveTest(){
        // 임의로 하나 생성해서 expected에 Null이 안 들어가 있는지 테스트
        var restaurantEntity = create();
        var expected = restaurantRepository.save(restaurantEntity);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1,expected.getIndex());
    }

    @Test
    public void updateTest(){
        // 임의로 하나 생성한 객체의 내용을 업데이트+저장한 후 내용이 바뀌었는지 테스트, 전체 사이즈가 안 바뀌었는지 테스트
        var restaurantEntity = create();
        var expected = restaurantRepository.save(restaurantEntity);

        expected.setTitle("update test");
        var saveEntity = restaurantRepository.save(expected);

        Assertions.assertEquals("update test", saveEntity.getTitle());
        Assertions.assertEquals(1,restaurantRepository.findAll().size());
    }

    @Test
    public void findByIdTest(){
        // 하나 저장하고 나서 index가 1번인 것을 찾아올 수 있는지 테스트
        var restaurantEntity = create();
        restaurantRepository.save(restaurantEntity);

        var expected = restaurantRepository.findById(1);

        Assertions.assertEquals(true,expected.isPresent()); // 안에 값이 있는지 체크
        Assertions.assertEquals(1, expected.get().getIndex()); // expected 의 값을 꺼냈을 때 index 가 1인지 체크
    }

    @Test
    public void deleteTest(){
        // 하나를 생성하고 지웠을 때 전체조회했을 때 갯수가 0개 인지 체크
        var restaurantEntity = create();
        restaurantRepository.save(restaurantEntity);

        restaurantRepository.deleteById(1);

        int count = restaurantRepository.findAll().size();

        Assertions.assertEquals(0,count);
    }

    @Test
    public void listAllTest(){
        // 2개 생성하고 전체조회 갯수가 2개인지 체크
        var restaurantEntity1 = create();
        restaurantRepository.save(restaurantEntity1);

        var restaurantEntity2 = create();
        restaurantRepository.save(restaurantEntity2);

        int count = restaurantRepository.findAll().size();

        Assertions.assertEquals(2,count);
    }
}
