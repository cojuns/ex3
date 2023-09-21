package org.zerock.ex3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex3.entity.MyData;
import org.zerock.ex3.repository.MyDataRepository;
import org.zerock.ex3.web.dto.SampleDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class Ex3ApplicationTests {

    @PersistenceContext()
    EntityManager em;

    @Autowired
    MyDataRepository myDataRepository;

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
    @Transactional
    @Test
    public void 엔티티매니저_연습(){
        MyData myData = em.find(MyData.class, 1L);
        System.out.println(myData);
        
        myData.setName("이기덕");
        myData = em.find(MyData.class, 1L);
        System.out.println(myData);

    }

    @Transactional
    @Test
    public void 엔티티매니저_연습_persist() {
        MyData d3 = new MyData();
        d3.setName("홍길동");
        d3.setAge(40);
        d3.setMail("hong@happy");
        d3.setMemo("홍메모..");
        em.persist(d3);

        TypedQuery<MyData> query =
                em.createQuery("SELECT m FROM MyData m", MyData.class);
        List<MyData> resultList = query.getResultList();
        for(MyData member : resultList){
            System.out.println("member = " + member);
        }

    }

    @Test
    public void List_mydata(){

        List<MyData> list = myDataRepository.getListDesc();
        list.stream().forEach(i -> {
            System.out.println(i);
        });

    }



}
