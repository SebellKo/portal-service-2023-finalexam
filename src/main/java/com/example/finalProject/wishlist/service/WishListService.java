package com.example.finalProject.wishlist.service;

import com.example.finalProject.naver.NaverClient;
import com.example.finalProject.naver.dto.SearchImageReq;
import com.example.finalProject.naver.dto.SearchLocalReq;
import com.example.finalProject.wishlist.dto.WishListDto;
import com.example.finalProject.wishlist.entity.WishListEntity;
import com.example.finalProject.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final NaverClient naverClient;
    private final WishListRepository wishListRepository; // db 생성
    public WishListDto search(String query) {
        // 지역검색
        var searchLocalReq = new SearchLocalReq(); // naver api 사용을 위해 request생성
        searchLocalReq.setQuery(query); // param으로 들어오는 query set

        var searchLocalRes = naverClient.searchLoacl(searchLocalReq); // 생성한 request이용 naver api 실행후 결과값 리턴.

        if (searchLocalRes.getTotal() > 0) { // 만약 결과가 있다면
            var localItem = searchLocalRes.getItems().stream().findFirst().get(); // 리턴값을 스트림화 시킨후 첫번째 결과를 리턴

            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", ""); // 결과값의 제목의 불필요한 문자 처리.
            var searchImageReq = new SearchImageReq(); // 이미지 검색 api 사용을 위한 request 생성
            searchImageReq.setQuery(imageQuery); // 이미지 검색 api의 쿼리 set
            // 이미지검색
            var searchImageRes = naverClient.searchImage(searchImageReq); // 이미지 검색 api 리턴값 반환

            if (searchImageRes.getTotal() > 0) { // 이미지 결과가 있다면
                var imageItem = searchImageRes.getItems().stream().findFirst().get();
                // 결과 리턴
                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setHomePageLink(localItem.getLink());
                result.setImageLink(imageItem.getLink());

                return result;
            }
        }
        return new WishListDto(); // 빈객체 리턴
    }

    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);
        var saveEntity = wishListRepository.save(entity);
        return entityToDto(saveEntity);
    }

    // entity는 db에 저장하기 위한 데이터이다. 따라서 클라이언트에게 주고 받은 데이터를 entity로 변환이 필요.
    private WishListEntity dtoToEntity(WishListDto wishListDto) {
        var entity = new WishListEntity();
        entity.setIndex(wishListDto.getIndex());
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getCategory());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());

        return entity;
    }
    // dto는 클라이언트에게 정보를 주고 받는데 필요하다 그렇기에 entity를 dto로 변환이 필요하다.
    private WishListDto entityToDto(WishListEntity wishListEntity) {
        var dto = new WishListDto();
        dto.setIndex(wishListEntity.getIndex());
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getCategory());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());

        return dto;
    }

    public List<WishListDto> findAll() {
        // 데이터베이스에 저장된 모든 WishListEntity를 조회하여 해당 엔티티들을 WishListDto로 변환후 리스트로 반환한다.
        return wishListRepository.findAll().stream().map(it -> entityToDto(it)).collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteById(index);
    }

    public void addVisit(int index) {
        var wishItem = wishListRepository.findById(index);
        if (wishItem.isPresent()) {
            var item = wishItem.get();

            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
