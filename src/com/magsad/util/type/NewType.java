package com.magsad.util.type;
import com.magsad.model.Type;

import java.util.Scanner;

public class NewType {
    public static Type getNewDepartment(){
        Type type = new Type();
        Scanner sc = new Scanner(System.in);
        System.out.print("id: ");
        type.setId(sc.nextInt());
        System.out.print("name: ");
        type.setName(sc.next());
        return type;
    }
}
