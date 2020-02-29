package ru.sibsutis;

import java.io.File;
import java.util.Random;

public class Main {
    private static void generateCSVs() {
        String[] nameTemplates = {"Алексей", "Кирилл", "Леонид", "Виктор", "Никита", "Дмитрий", "Максим", "Артур",
                "Александр", "Вячеслав", "Сергей", "Евкакий", "Мухаммад"};
        String[] emailTemplates = {"lolimpo@gmail.com", "snoweee322@rambler.ru", "kkirrill@mail.ru",
                "soul-catcher@yandex.ru", "gbggtw@mail.com", "izeytee@yahoo.com", "realtura@hotmail.com",
                "eios@sibsutis.ru"};
        String[] projectTemplates = {"Разработка", "Поддержка", "Дебаггинг", "Бухгалтерия", "Уборка", "Доставка",
                "Менеджмент"};
        String[] managingLevelTemplates = {"Старший-менеджер", "Средний-менеджер", "Младший-менеджер", "Стажер",
                "Секретарь"};

        Random random = new Random();
        Developer[] developer = new Developer[1000];
        Manager[] manager = new Manager[1000];
        developer[0] = new Developer("Name","Email","Project"); developer[0].toCSV();
        manager[0] = new Manager("Name", "Email", "Level"); manager[0].toCSV();
        for(int i = 0; i < 1000; i++) {
            developer[i] = new Developer(nameTemplates[random.nextInt(nameTemplates.length)],
                    emailTemplates[random.nextInt(emailTemplates.length)],
                    projectTemplates[random.nextInt(projectTemplates.length)]);
            manager[i] = new Manager(nameTemplates[random.nextInt(nameTemplates.length)],
                    emailTemplates[random.nextInt(emailTemplates.length)],
                    managingLevelTemplates[random.nextInt(managingLevelTemplates.length)]);
            developer[i].toCSV();
            manager[i].toCSV();
        }

    }

    public static void main(String[] args) {
       try {
           File developersCSV = new File("/home/lolimpo/Projects/java_courses/javaTask2/", "developers.csv");
           File managersCSV = new File("/home/lolimpo/Projects/java_courses/javaTask2/", "managers.csv");
           if(developersCSV.createNewFile() || managersCSV.createNewFile())
               generateCSVs();
       } catch(Exception e) {
           System.err.println(e);
        }
    }
}
