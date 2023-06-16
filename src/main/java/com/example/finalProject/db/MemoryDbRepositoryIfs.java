package com.example.finalProject.db;

import java.util.List;
import java.util.Optional;

// 제네릭을 사용하여 다양한 타입의 데이터를 처리한다.
// 제네릭을 사용한다면 클래스나 인터페이스 선언시, 사용할 타입을 미리 정의하지 않고, 실제 사용 시에 타입을 지정할 수 있기에 재사용성과 유연성을 높일수 있다.
public interface MemoryDbRepositoryIfs <T> { // 제네릭 이용 타입 지정
    Optional<T> findById(int index);
    T save(T entity);
    void deleteById(int index);
    List<T> findAll();
}

