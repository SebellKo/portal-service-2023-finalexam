package com.example.finalProject.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 파라미터 없는 기본 생성자를 자동으로 생성해준다.
@AllArgsConstructor // 모든 필드를 가지는 생성자를 자동으로 생성해 준다.
@Data // 클래스에 대한 Getter, Setter, equals(), hashCode(), toString() 등의 메서드를 자동을 ㅗ생성
public class MemoryDbEntity {
    protected Integer index;
}
