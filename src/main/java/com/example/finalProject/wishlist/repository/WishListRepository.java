package com.example.finalProject.wishlist.repository;

import com.example.finalProject.db.MemoryDbRepositoryAbstract;
import com.example.finalProject.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository // db를 저장하는 곳임을 지정
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> { // T 매개변수로 WishListEntity 전달.
}
