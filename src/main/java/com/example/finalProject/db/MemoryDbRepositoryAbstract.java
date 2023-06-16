package com.example.finalProject.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 실제 데이터베이스 대신 메모리에 데이터를 저장하고 관리.
abstract public class MemoryDbRepositoryAbstract <T extends MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{ // MemoryDbRepositoryIfs 상속
    private final List<T> db = new ArrayList<>(); // 데이터가 저장될 리스트이며 MemoryDbEntity 클래스를 상속받은 클래스.
    private int index = 0;

    // index에 해당하는 엔티티를 찾아 Optional 객체로 반환한다.
    @Override
    public Optional<T> findById(int index) { // Optional = 데이터가 있을수도 있고 없을수도 있다. 없을경우 빈객체를 반환한다.
        return db.stream().filter(it -> it.getIndex() == index).findFirst(); // db 리스트를 스트림으로 변환하여 인덱스가 일치하는 첫 번째 엔티티를 찾고 반환한다.
    }

    // 주어진 엔티티를 저장하거나 업데이트 한다.
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
