package com.grefort.news.job;

import com.grefort.news.models.News;
import com.grefort.news.service.NewsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseTask {
    @Autowired
    NewsService newsService;

    @Scheduled(fixedDelay = 300000)
    public void parseNews(){
        newsService.clearall();
        System.out.println("База данных очищена");
        String url = "https://yandex.ru/news/";
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla")
                    .timeout(5000)
                    .referrer("https:google.com")
                    .get();
            Elements news = doc.getElementsByClass("mg-card__title"); // Заголовок
            Elements news1 = doc.getElementsByClass("mg-card__annotation"); // Краткое содержание
            int size = news.size();
            for(int i = 0; i <size; i++){
                String title = news.get(i).ownText();
                String body = news1.get(i).ownText();
                if (!newsService.isAxist(title)) {
                    if (body.length() < 255) {
                        News obj = new News();
                        obj.setTitle(title);
                        obj.setNewsbody(body);
                        newsService.save(obj);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("База данных заполнена");

    }
}
