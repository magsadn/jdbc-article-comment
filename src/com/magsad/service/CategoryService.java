package com.magsad.service;

import com.magsad.model.Article;
import com.magsad.model.Category;
import com.magsad.repository.ArticleRepository;
import com.magsad.repository.CategoryRepository;
import com.magsad.util.article.UpdateArticle;
import com.magsad.util.category.NewCategory;
import com.magsad.util.category.UpdateCategory;

import java.util.List;
import java.util.Scanner;

public class CategoryService {
    private static CategoryRepository categoryRepository = new CategoryRepository();
    private static ArticleRepository articleRepository = new ArticleRepository();
    public void getCategory() {
        do {
            System.out.println("\n1.New 2.FindAll 3.FindById 4.Delete 5.Update 0.Exit");
            System.out.print("Enter Main/Category select: ");
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
        List<Category> categoryList = categoryRepository.findAll();
        categoryList.forEach(category -> System.out.print(getPrint(category)));
    }

    private static void findById() {
        System.out.print("Enter category id: ");
        int id = new Scanner(System.in).nextInt();
        Category category = categoryRepository.findById(id);
        System.out.println(getPrint(category));
//        System.out.println(category);
    }

    private static void save() {
        Category category = NewCategory.getNewCategory();
//        category.setArticleList());
        System.out.println(categoryRepository.save(category));
    }

    private static void update() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter category id: ");
        int id = sc.nextInt();
        Category category = categoryRepository.findById(id);
        System.out.println(getPrint(category));
        UpdateCategory.updateCategory(category);
        System.out.println(categoryRepository.update(category));
    }

    private static void delete() {
        System.out.print("Enter category id: ");
        int id = new Scanner(System.in).nextInt();
        Category category = categoryRepository.findById(id);
        categoryRepository.delete(category);
    }

    private static String getPrint(Category category) {
        return String.format("%4d |%20s |\n",
                category.getId(),
                category.getName()
        );
    }
}
