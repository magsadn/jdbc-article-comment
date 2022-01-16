package com.magsad;

import com.magsad.model.Article;
import com.magsad.repository.CategoryRepository;
import com.magsad.service.ArticleService;
import com.magsad.service.CategoryService;
import com.magsad.service.CommentService;
import com.magsad.service.TypeService;

import java.util.Scanner;

public class Main {
    private static TypeService typeService = new TypeService();
    private static ArticleService articleService = new ArticleService();
    private static CommentService commentService = new CommentService();
    private static CategoryService categoryService = new CategoryService();

    public static void main(String[] args) {
        do {
            System.out.println("\n1.Type 2.Article 3.Comment 4.Category 0.Exit");
            System.out.print("Enter Main/select: ");
            int select = new Scanner(System.in).nextInt();
            if (select == 4){
                System.out.println("\n--Category Section--");
                categoryService.getCategory();
            }
            if (select == 3){
                System.out.println("\n--Comment Section--");
                commentService.getComment();
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
