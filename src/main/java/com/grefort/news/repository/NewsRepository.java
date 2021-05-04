package com.grefort.news.repository;

import com.grefort.news.models.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT p FROM News p WHERE p.title LIKE %?1% " +
            "or p.newsbody LIKE %?1%")
    public List<News> search(String keyword);
}
