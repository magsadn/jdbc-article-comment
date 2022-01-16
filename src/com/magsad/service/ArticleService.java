package com.magsad.service;

import com.magsad.model.Article;
import com.magsad.repository.ArticleRepository;
import com.magsad.repository.TypeRepository;
import com.magsad.util.article.NewArticle;
import com.magsad.util.article.UpdateArticle;

import java.util.List;
import java.util.Scanner;

public class ArticleService {
    private static final ArticleRepository articleRepository = new ArticleRepository();
    public static final TypeRepository typeRepository = new TypeRepository();
    public void getArticle() {
        do {
            System.out.println("\n1.New 2.FindAll 3.FindById 4.Delete 5.Update 0.Exit");
            System.out.print("Enter Main/Article select: ");
            int select = new Scanner(System.in).nextInt();
            if (select == 5) {
                update();
            }
            if (select == 4) {
                delete();
            }
            if (select == 3) {
                findById();
            }
            if (select == 2) {
                findAll();
            }
            if (select == 1) {
                save();
            }
            if (select == 0) {
                break;
            }
        } while (true);
    }

    private static void findAll() {
        List<Article> articleList = articleRepository.findAll();
        System.out.format("%4s |%20s |%15s |%15s |%12s |%12s |%18s |%10s |%5s |%6s |\n",
                "Id","HeadLine","ArticleAbstract","MainText","DateCreated","DateAmended","PostedBy","MakePublic","Views","TypeId"

        );
        articleList.forEach(article -> System.out.print(getPrint(article)));
//        articleList.forEach(article -> System.out.print(article));
    }

    private static void findById() {
        System.out.print("Enter article id: ");
        int id = new Scanner(System.in).nextInt();
        Article article = articleRepository.findById(id);
        System.out.println(getPrint(article));
//        System.out.println(article);
    }

    private static void save() {
        Article article = NewArticle.getNewArticle();
        System.out.print("typeId: ");
        article.setType(typeRepository.findById(new Scanner(System.in).nextInt()));
//        article.setCategoryList();
        System.out.println(articleRepository.save(article));
    }

    private static void update() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter article id: ");
        int id = sc.nextInt();
        Article article = articleRepository.findById(id);
        System.out.println(getPrint(article));
        UpdateArticle.updateArticle(article);
        System.out.println(articleRepository.update(article));
    }

    private static void delete() {
        System.out.print("Enter article id: ");
        int id = new Scanner(System.in).nextInt();
        Article article = articleRepository.findById(id);
        articleRepository.delete(article);
    }

    private static String getPrint(Article art) {
        return String.format("%4d |%20s |%15s |%15s |%12s |%12s |%18s |%10b |%5d |%6d |\n",
                art.getId(),
                art.getHeadline(),
                art.getArticleAbstract(),
                art.getMainText(),
                art.getDateCreated(),
                art.getDateAmended(),
                art.getPostedBy(),
                art.getMakePublic(),
                art.getViews(),
                art.getType().getId()
        );
    }
}
