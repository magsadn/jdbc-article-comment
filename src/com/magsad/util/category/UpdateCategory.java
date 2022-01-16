package com.magsad.util.category;
import com.magsad.model.Category;

import java.util.Scanner;

public class UpdateCategory {
    public static Category updateCategory(Category category) {
        do {
            System.out.println(" 1.Name 0.Exit" );
            System.out.print("Enter Category/Update select: ");
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            if (select == 1) {
                System.out.print("name: ");
                category.setName(sc.next());
            }
            if (select == 0) {
                return category;
            }
        } while (true);

    }
}
