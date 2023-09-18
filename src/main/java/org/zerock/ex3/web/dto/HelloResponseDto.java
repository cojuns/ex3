package org.zerock.ex3.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor // final만 처리
public class HelloResponseDto {

    private final String name;
    private final int amount;


}
