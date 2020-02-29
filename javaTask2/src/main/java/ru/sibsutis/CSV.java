package ru.sibsutis;

interface CSV {
    void toCSV();
    String fromCSV(Integer index);
    void removeFromCSV(Integer index);
}