package com.example.restaurantlist.controller;

import com.example.restaurantlist.restaurant.dto.RestaurantDto;
import com.example.restaurantlist.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/restaurant")
@RequiredArgsConstructor
public class ApiController {

    private final RestaurantService restaurantService;

    @GetMapping("/search")
    public RestaurantDto search(@RequestParam String query){
        return restaurantService.search(query);
    }

    @PostMapping("")
    public RestaurantDto add(@RequestBody RestaurantDto restaurantDto){
        log.info("restaurantDto : {}",restaurantDto);

        return restaurantService.add(restaurantDto);
    }

    @GetMapping("/all")
    public List<RestaurantDto> findAll(){
        return restaurantService.findAll();
    }

    @DeleteMapping("/{index}")
    public void delete(@PathVariable int index){
        restaurantService.delete(index);
    }

    @PostMapping("/{index}")
    public void addVisit(@PathVariable int index){
        restaurantService.addVisit(index);
    }

}
