package com.magsad;

import com.magsad.model.Article;
import com.magsad.service.ArticleService;
import com.magsad.service.TypeService;

import java.util.Scanner;

public class Main {
    private static TypeService typeService = new TypeService();
    private static ArticleService articleService = new ArticleService();

    public static void main(String[] args) {
        do {
            System.out.println("\n1.Type 2.Article 3.Comment 4.Category 0.Exit");
            System.out.print("Enter Main/select: ");
            int select = new Scanner(System.in).nextInt();
            if (select == 4){
                System.out.println("\n--Category Section--");
            }
            if (select == 3){
                System.out.println("\n--Comment Section--");
            }
            if (select == 2){
                System.out.println("\n--Article Section--");
                articleService.getArticle();
            }if (select == 1){
                System.out.println("\n--Type section--");
                typeService.getType();
            }if (select == 0){
                break;
            }
        } while (true);
    }
}
