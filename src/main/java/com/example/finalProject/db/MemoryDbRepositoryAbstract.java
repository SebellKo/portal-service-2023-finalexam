package com.example.finalProject.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract <T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{ // MemoryDbRepositoryIfs 상속
    private final List<T> db = new ArrayList<>();
    private int index = 0;
    @Override
    public Optional<T> findById(int index) { // Optional = 데이터가 있을수도 있고 없을수도 있다.
        return db.stream().filter(it -> it.getIndex() == index).findFirst(); // it = MemoryDbEntity의 index
    }

    @Override
    public T save(T entity) {

        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if (optionalEntity.isEmpty()) { // db에 데이터가 없는 경우
         index++;
         entity.setIndex(index);
         db.add(entity);
         return entity;

        }else { // db에 이미 데이터가 있는 경우
            var preIndex = optionalEntity.get().getIndex(); // 그전에 있던 데이터 index
            entity.setIndex(preIndex);

            deleteById(preIndex); // 이전의 데이터 삭제
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst(); // optional한 객체 찾아오기
        if (optionalEntity.isPresent()) { // entity가 있다면
            db.remove(optionalEntity.get()); // 해당 데이터 객체 삭제
        }
    }

    @Override
    public List<T> findAll() {
        return  db;
    }
}
