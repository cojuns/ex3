package org.zerock.ex3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zerock.ex3.entity.MyData;

@Repository
public interface MyDataRepository  extends JpaRepository<MyData, Long> {

}
