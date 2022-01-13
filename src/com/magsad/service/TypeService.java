package com.magsad.service;

import com.magsad.model.Type;
import com.magsad.repository.TypeRepository;
import com.magsad.util.type.NewType;
import com.magsad.util.type.UpdateType;

import java.util.List;
import java.util.Scanner;

public class TypeService {
    private static TypeRepository typeRepository = new TypeRepository();

    public void getType() {
        do {
            System.out.println("\n1.New 2.FindAll 3.FindById 4.Delete 5.Update 0.Exit");
            System.out.print("Enter Main/Type select: ");
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
        List<Type> typeList = typeRepository.findAll();
        typeList.stream().forEach(type -> System.out.print(getPrint(type)));
    }

    private static void findById() {
        System.out.print("Enter type id: ");
        int id = new Scanner(System.in).nextInt();
        Type type = typeRepository.findById(id);
        System.out.println(getPrint(type));
    }

    private static void save() {
        Type type = NewType.getNewDepartment();
        System.out.println(typeRepository.save(type));
    }

    private static void update() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter type id: ");
        int id = sc.nextInt();
        Type type = typeRepository.findById(id);
        System.out.println(getPrint(type));
        UpdateType.updateType(type);
        System.out.println(typeRepository.update(type));
    }

    private static void delete() {
        System.out.print("Enter type id: ");
        int id = new Scanner(System.in).nextInt();
        Type type = typeRepository.findById(id);
        typeRepository.delete(type);
    }

    private static String getPrint(Type type) {
        return String.format("%4d | %40s |\n",
                type.getId(),
                type.getName()
        );
    }
}
