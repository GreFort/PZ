package com.grefort.news.api;

import com.grefort.news.models.News;
import com.grefort.news.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;



@Controller
public class NewsController {

    @Autowired
    NewsRepository newsRepository;
    @GetMapping(value = "/news")
    public String news(Model model)
    {
        Iterable<News> news =  newsRepository.findAll();
        model.addAttribute("news",news);
        return "news";
    }
    @RequestMapping(value = "/db")
    public String db(Model model, @Param("keyword") String keyword) throws InterruptedException
    {
        Iterable<News> news = newsRepository.search(keyword);
        model.addAttribute("news",news);
        return "news";
    }
}

