package ru.sibsutis;

public class Developer extends User implements CSV {
    private String project;

    public String getProject() {
        return this.project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Developer(String name, String email, String project) {
        super(name, email);
        this.project = project;
    }

    public Developer() {}

    @Override
    public String toCSV() {
        return getName() + ',' + getEmail() + ',' + getProject();
    }

    @Override
    public void fromCSV(String str) {
        String[] data = new String[3];
        data = str.split(";");
        setName(data[0]);
        setEmail(data[1]);
        setProject(data[3]);
    }
}