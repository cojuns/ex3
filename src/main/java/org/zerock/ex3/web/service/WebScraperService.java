package org.zerock.ex3.web.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class WebScraperService {
    public void scrapeWebsite(Model model) {

        try {
            // 웹 페이지 URL 지정
            String url = "https://prod.danawa.com/info/?pcode=19627808&cate=112747&adinflow=Y";

            // 웹 페이지 가져오기
            Document doc = Jsoup.connect(url).get();


            Element element = doc.selectFirst("em.prc_c");

            // 요소의 내용 (텍스트) 가져오기
            assert element != null;
            String value = element.ownText();

            System.out.println("가격: " + value);
            model.addAttribute("scrapeData", value);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
