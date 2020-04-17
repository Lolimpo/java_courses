package ru.sibsutis;

public class Manager extends User implements CSV {
    private String level;
    private Integer KPI;

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getKPI() {
        return this.KPI;
    }

    public void setKPI(Integer KPI) {
        this.KPI = KPI;
    }

    public Manager(String name, String email, String level, Integer KPI) {
        super(name, email);
        this.level = level;
        this.KPI = KPI;
    }

    public Manager() {}

    @Override
    public String toCSV() {
        return getName() + ',' + getEmail() + ',' + getLevel() + ',' + getKPI();
    }

    @Override
    public void fromCSV(String str) {
        String[] data = new String[4];
        data = str.split(";");
        setName(data[0]);
        setEmail(data[1]);
        setLevel(data[2]);
        setKPI(Integer.valueOf(data[3]));
    }
}