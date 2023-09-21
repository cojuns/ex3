package org.zerock.ex3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.zerock.ex3.entity.MyData;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MyDataRepository  extends JpaRepository<MyData, Long> {

    @Query("select m from MyData m order by m.id desc ")
    List<MyData> getListDesc();

    @Transactional
    @Modifying
    @Query("update MyData x set x.memo = :memo where x.id = :id")
    int updateMyData(@Param("memo") String memo, @Param("id") Long id);
}
