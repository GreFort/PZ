package com.grefort.news.service;

import com.grefort.news.models.News;
import com.grefort.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NewsServiceImpl implements NewsService{

    @Autowired
    NewsRepository repository;
    @Override
    public void save(News news) {
        repository.save(news);
    }
    @Override
    public void clearall(){repository.deleteAll();}
    @Override
    public boolean isAxist(String newsTitle) {
        List<News> news = repository.findAll();
        for(News n: news){
            if(n.getTitle().equals(newsTitle)){
                return true;
            }
        }
        return false;
    }
    @Override
    @Cacheable(value = "cache")
    public List<News> getAllNews() {
        return repository.findAll();
    }
}
