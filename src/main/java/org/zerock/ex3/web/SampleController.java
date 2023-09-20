package org.zerock.ex3.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.ex3.web.dto.SampleDTO;
import org.zerock.ex3.web.service.WebScraperService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/sample")
@Log4j2
public class SampleController {

    @Autowired
    WebScraperService webScraperService;

    @GetMapping("/ex1")
    public void ex1(){
        log.info("ex1...............");
    }

    @GetMapping({"/ex2", "/ex22", "/ex23", "/ex24",
            "/ex25", "/ex26", "/ex27","/ex28",
            "/ex29","/exLink", "/exDate", "/exDate2"})
    public void ex2(Model model){

        List<SampleDTO> list2 = new ArrayList<>();
        for(int i = 1; i<= 20; i++){
            SampleDTO dto = SampleDTO.builder()
                    .sno((long) i).first("First.." +i).last("Last.."+i)
                    .regTime(LocalDateTime.now()).build();
            list2.add(dto);
        }



        // 컬렉션 스트림을 이용하여 1~20까지 정수를 발생, 발생한 정수를 Long 타입으로
        // 변경하여 람다식을 통해 dto 객체를 생성합니다.
        List<SampleDTO> list = IntStream.rangeClosed(1, 20)
                .asLongStream().mapToObj(i -> {
                    SampleDTO dto = SampleDTO.builder()
                            .sno(i)
                            .first("First.." + i)
                            .last("Last.." + i)
                            .regTime(LocalDateTime.now())
                            .build();
                    return dto;
                }).collect(Collectors.toList());
        model.addAttribute("list", list);

    }



    @GetMapping({"/exInline"})
    public String exInline(RedirectAttributes redirectAttributes){
        //리다이렉트 직전에 플래시에 저장하는 메소드
        // 리다이렉트 이후에 소멸됨
        SampleDTO dto = SampleDTO.builder()
                .sno(100L)
                .first("First..100")
                .last("Last..100")
                .regTime(LocalDateTime.now())
                .build();
        redirectAttributes.addFlashAttribute("result", "success");
        redirectAttributes.addFlashAttribute("dto", dto);


        return "redirect:/sample/ex30";
    }

    @GetMapping("/ex31")
    public String ex3(Model model){
        webScraperService.scrapeWebsite(model);
        return "/sample/ex31";


    }

    @GetMapping({"/exLayout1", "/exSidebar"})
    public void exLayout(){
        log.info("exLayout................");
    }

}
