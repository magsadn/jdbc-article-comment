package com.magsad.util.type;
import com.magsad.model.Type;

import java.util.Scanner;

public class UpdateType {
    public static Type updateType(Type type) {
        do {
            System.out.println(" 1.Name 0.Exit");
            System.out.print("Enter Update/select: ");
            Scanner sc = new Scanner(System.in);
            int select = sc.nextInt();
            
            if (select == 1) {
                System.out.print("name: ");
                type.setName(sc.next());
            }
            if (select == 0) {
                return type;
            }
        } while (true);

    }
}
