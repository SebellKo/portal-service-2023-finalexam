package com.example.finalProject.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs <T> { // 제네릭 이용 타입 지정
    Optional<T> findById(int index);
    T save(T entity);
    void deleteById(int index);
    List<T> findAll();
}

