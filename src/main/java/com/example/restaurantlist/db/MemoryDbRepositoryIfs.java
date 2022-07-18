package com.example.restaurantlist.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {
    // type 을 찾아서 return하는 메서드
    Optional<T> findById(int index);

    // 저장 메서드
    T save(T entity);

    // 삭제 메서드
    void deleteById(int index);

    // 리스트 전체 return
    List<T> findAll();
}
