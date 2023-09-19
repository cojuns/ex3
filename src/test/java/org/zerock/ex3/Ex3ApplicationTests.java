package org.zerock.ex3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex3.web.dto.SampleDTO;

import java.time.LocalDateTime;

@SpringBootTest
class Ex3ApplicationTests {

    @Test
    void contextLoads() {
        SampleDTO dto = SampleDTO.builder()
                .first("First")
                .last("Last")
                .regTime(LocalDateTime.now())
                .sno(1L)
                .build();
        System.out.println(dto);

        SampleDTO dto2 = dto.toBuilder()
                            .sno(2L)
                .regTime(LocalDateTime.now())
                .build();
        System.out.println(dto2);

    }

}
