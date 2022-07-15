package com.example.restaurantlist.restaurant.repositroy;

import com.example.restaurantlist.db.MemoryDbRepositoryAbstract;
import com.example.restaurantlist.restaurant.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

@Repository // Repository, 즉 db를 저장하는 곳이다~라고 지정
public class RestaurantRepository extends MemoryDbRepositoryAbstract<RestaurantEntity> {
}
