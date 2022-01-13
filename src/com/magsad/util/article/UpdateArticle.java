package com.magsad.util.article;
import com.magsad.model.Article;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UpdateArticle {
    public static Article updateArticle(Article article) {
        do {
            System.out.println(" 1.Headline 2.ArticleAbstract 3.MainText 4.DateCreated 5.DateAmended 6.PostedBy 7.MakePublic 8.Views 9.TypeId  0.Exit");
            System.out.print("Enter Update/select: ");
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            if (select == 9) {
                System.out.print("typeId: ");
//                article.setType();
            }
            if (select == 8) {
                System.out.print("views: ");
                article.setViews(sc.nextInt());
            }
            if (select == 7) {
                System.out.print("makePublic: ");
                article.setMakePublic(sc.nextBoolean());
            }
            if (select == 6) {
                System.out.print("postedBy: ");
                article.setPostedBy(sc.next());
            }
            if (select == 5) {
                System.out.print("dateAmended: ");
                String dateInString2 = sc.next();
                LocalDate date2 = LocalDate.parse(dateInString2, DateTimeFormatter.BASIC_ISO_DATE);
            }
            if (select == 4) {
                System.out.print("dateCreated: ");
                String dateInString1 = sc.next();
                LocalDate date1 = LocalDate.parse(dateInString1, DateTimeFormatter.BASIC_ISO_DATE);
            }
            if (select == 3) {
                System.out.print("mainText: ");
                article.setMainText(sc.next());
            }
            if (select == 2) {
                System.out.print("articleAbstract: ");
                article.setArticleAbstract(sc.next());
            }
            if (select == 1) {
                System.out.print("headline: ");
                article.setHeadline(sc.next());
            }
            if (select == 0) {
                return article;
            }
        } while (true);

    }
}
