package com.magsad.util.category;
import com.magsad.model.Category;

import java.util.Scanner;

public class NewCategory {
    public static Category getNewCategory(){
        Category category = new Category();
        Scanner sc = new Scanner(System.in);
        System.out.print("id: ");
        category.setId(sc.nextInt());
        System.out.print("name: ");
        category.setName(sc.next());
        return category;
    }
}
