package ru.sibsutis;

public class Main {
    public static void main(String[] args) {
        Developer dev1 = new Developer("Nikita", "lolimpo@gmail.com", "java_courses", 88);
        Developer dev2 = new Developer("Poop", "poop@kak.com", "pooping", 14);
        Developer dev3 = new Developer("John", "john@smith.com", "default", 62);
        StatisticKPI<Developer> statistics = new StatisticKPI<>();
        Developer maxKPI = statistics.getMaxKPI(dev1, dev2, dev3);
        Developer minKPI = statistics.getMinKPI(dev1, dev2, dev3);
        Developer avgKPI = statistics.getAvgKPI(dev1, dev2, dev3);
        System.out.println("Maximal KPI: " + maxKPI.getKPI() + "\nMinimal KPI: " + minKPI.getKPI() + "\nAverage KPI: " + avgKPI.getKPI());
    }
}
