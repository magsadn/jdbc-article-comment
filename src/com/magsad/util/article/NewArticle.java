package com.magsad.util.article;
import com.magsad.model.Article;
import com.magsad.model.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NewArticle {
    public static Article getNewArticle(){
        Article article = new Article();
        Scanner sc = new Scanner(System.in);
        System.out.print("id: ");
        article.setId(sc.nextInt());
        System.out.print("headline: ");
        article.setHeadline(sc.next());
        System.out.print("articleAbstract: ");
        article.setArticleAbstract(sc.next());
        System.out.print("mainText: ");
        article.setMainText(sc.next());
        System.out.print("dateCreated (example-20220123): ");
        String dateInString1 = sc.next();
        LocalDate date1 = LocalDate.parse(dateInString1, DateTimeFormatter.BASIC_ISO_DATE);
        article.setDateCreated(date1);
        System.out.print("dateAmended (example-20220123): ");
        String dateInString2 = sc.next();
        LocalDate date2 = LocalDate.parse(dateInString2, DateTimeFormatter.BASIC_ISO_DATE);
        article.setDateAmended(date2);
        System.out.print("postedBy: ");
        article.setPostedBy(sc.next());
        System.out.print("makePublic (true/false): ");
        article.setMakePublic(sc.nextBoolean());
        System.out.print("views: ");
        article.setViews(sc.nextInt());
//        setTypeId is in ArticleService.java
        return article;
    }
}
