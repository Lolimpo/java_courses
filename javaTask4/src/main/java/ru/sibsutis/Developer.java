package ru.sibsutis;

public class Developer extends User implements CSV {
    private String project;
    private Integer KPI;

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getKPI() {
        return KPI;
    }

    public void setKPI(Integer KPI) {
        this.KPI = KPI;
    }

    public Developer(String name, String email, String project, Integer KPI) {
        super(name, email);
        this.project = project;
        this.KPI = KPI;
    }

    public Developer() {}

    @Override
    public String toCSV() {
        return getName() + ',' + getEmail() + ',' + getProject() + ',' + getKPI();
    }

    @Override
    public void fromCSV(String str) {
        String[] data = new String[4];
        data = str.split(";");
        setName(data[0]);
        setEmail(data[1]);
        setProject(data[3]);
        setKPI(Integer.valueOf(data[4]));
    }
}