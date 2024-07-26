package com.jjang051.repository;

import com.jjang051.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Integer> {


    @Query("SELECT s FROM Story s left join fetch s.member m where m.id = :id ")
    List<Story> findAllId(@Param("id") int id);  //내가 쓴 글만 들고 오겠다.
    @Query("SELECT s FROM Story s left join fetch s.member m where m.userId = :userId")
    List<Story> findAllUserId(@Param("userId") String userId);  //내가 쓴 글만 들고 오겠다.
}
