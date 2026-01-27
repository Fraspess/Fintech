package org.example;

import org.example.classes.CategoryRepository;
import org.example.entities.CategoryEntity;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //System.out.printf("Hello and welcome!");
        CategoryRepository categoryRepository = new CategoryRepository();
        var scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:\n1 - Подивитись всі категорії\n2 - Додати категорію\n3 - Видалити категорію\n4 - Редагувати категорію");
            String choice = scanner.next();
            switch(choice) {
                case "1":
                    System.out.println("Всі категорії:");
                    var categories = categoryRepository.getCategories();
                    for(CategoryEntity category : categories){
                        System.out.println("---------------------------------------\nId - " + category.getId() + "\nName - " + category.getName() + "\nDate created: " + category.getDateCreated());
                    }
                    break;
                case "2": {
                    System.out.println("Введіть ім'я категорії: ");
                    String name = scanner.next();
                    categoryRepository.addCategory(name);
                    break;
                }
                case "3": {
                    System.out.println("Введіть id категорії: ");
                    int id = Integer.parseInt(scanner.next());
                    categoryRepository.removeCategory(id);
                    break;
                }
                case "4": {
                    System.out.println("Введіть id категорії: ");
                    int id = Integer.parseInt(scanner.next());
                    System.out.println("Введіть нове ім'я категорії: ");
                    String name = scanner.next();
                    categoryRepository.updateCategory(id,name);
                    break;
                }
                default:
                    System.out.println("Неправильна операція. Спробуйте ще раз.");
            }


        }
    }
}