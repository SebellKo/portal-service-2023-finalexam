package com.example.finalProject.naver;

import com.example.finalProject.naver.dto.SearchImageReq;
import com.example.finalProject.naver.dto.SearchImageRes;
import com.example.finalProject.naver.dto.SearchLocalReq;
import com.example.finalProject.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {
    @Value("${naver.client.id}")
    private String naverClientId;
    @Value("${naver.client.secret}")
    private String naverClientSecret;
    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;
    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    public SearchLocalRes searchLoacl(SearchLocalReq searchLocalReq) {
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl).queryParams(searchLocalReq.toMultiValueMap()).build().encode().toUri(); // api 요청 주소

        var headers = new HttpHeaders(); // hearder 준비
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers); // request 타입
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){}; // response 타입

        var responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType); // 결과 얻어오기.

        return responseEntity.getBody(); // 결과 리턴
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq) {
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl).queryParams(searchImageReq.toMultiValueMap()).build().encode().toUri(); // api 요청 주소

        var headers = new HttpHeaders(); // hearder 준비
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers); // request 타입
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){}; // response 타입

        var responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType); // 결과 얻어오기.

        return responseEntity.getBody(); // 결과 리턴
    }
}
