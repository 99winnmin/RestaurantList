package com.example.restaurantlist.restaurant.service;

import com.example.restaurantlist.naver.NaverClient;
import com.example.restaurantlist.naver.dto.SearchImgReq;
import com.example.restaurantlist.naver.dto.SearchLocalReq;
import com.example.restaurantlist.restaurant.dto.RestaurantDto;
import com.example.restaurantlist.restaurant.entity.RestaurantEntity;
import com.example.restaurantlist.restaurant.repositroy.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final NaverClient naverClient;
    private final RestaurantRepository restaurantRepository;


    // query 를 이용해서 controller를 통해 req가 들어오면 결과를 return 함
    public RestaurantDto search(String query){
        // 지역 검색
        var serachLocalReq = new SearchLocalReq();
        serachLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(serachLocalReq);

        // 지역 검색의 내용이 있을 때만 이미지 검색
       if(searchLocalRes.getTotal() > 0){
            var localItem = searchLocalRes.getItems().stream().findFirst().get();

            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>",""); // 불필요한 글자 없애는 정규식
            var searchImageReq = new SearchImgReq();
            searchImageReq.setQuery(imageQuery);

            // 이미지 검색
            var searchImageRes = naverClient.searchImage(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                // 결과를 리턴
                var result = new RestaurantDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());
                return result;
            }
        }
        return new RestaurantDto();
    }

    // memoryDB를 사용하기 위한 약간의 노가다 코딩..
    public RestaurantDto add(RestaurantDto restaurantDto) {
        // dto -> entity로 변경 후 db 저장
        var entity = dtoToEntity(restaurantDto);
        var saveEntity = restaurantRepository.save(entity);
        return entityToDto(saveEntity);
    }

    private RestaurantEntity dtoToEntity(RestaurantDto restaurantDto){
        var entity = new RestaurantEntity();
        entity.setIndex(restaurantDto.getIndex());
        entity.setTitle(restaurantDto.getTitle());
        entity.setCategory(restaurantDto.getCategory());
        entity.setAddress(restaurantDto.getAddress());
        entity.setRoadAddress(restaurantDto.getRoadAddress());
        entity.setHomePageLink(restaurantDto.getHomePageLink());
        entity.setImageLink(restaurantDto.getImageLink());
        entity.setVisit(restaurantDto.isVisit());
        entity.setVisitCount(restaurantDto.getVisitCount());
        entity.setLastVisitDate(restaurantDto.getLastVisitDate());
        return entity;
    }

    private RestaurantDto entityToDto(RestaurantEntity restaurantEntity){
        var dto = new RestaurantDto();
        dto.setIndex(restaurantEntity.getIndex());
        dto.setTitle(restaurantEntity.getTitle());
        dto.setCategory(restaurantEntity.getCategory());
        dto.setAddress(restaurantEntity.getAddress());
        dto.setRoadAddress(restaurantEntity.getRoadAddress());
        dto.setHomePageLink(restaurantEntity.getHomePageLink());
        dto.setImageLink(restaurantEntity.getImageLink());
        dto.setVisit(restaurantEntity.isVisit());
        dto.setVisitCount(restaurantEntity.getVisitCount());
        dto.setLastVisitDate(restaurantEntity.getLastVisitDate());
        return dto;
    }

    public List<RestaurantDto> findAll() {
        return restaurantRepository.findAll()
                .stream()
                .map(it -> entityToDto(it))
                .collect(Collectors.toList());
    }

    public void delete(int index) {
        restaurantRepository.deleteById(index);
    }

    public void addVisit(int index){
        var restaurantItem = restaurantRepository.findById(index);
        if(restaurantItem.isPresent()){
            var item = restaurantItem.get();

            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
