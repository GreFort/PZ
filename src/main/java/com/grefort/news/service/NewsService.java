package com.grefort.news.service;

import com.grefort.news.models.News;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
    public void save(News news); // сохранение
    public void clearall(); // удаление
    public boolean isAxist(String newsTitle); // проверка на наличие
    public List<News> getAllNews(); // сбор данных

}
