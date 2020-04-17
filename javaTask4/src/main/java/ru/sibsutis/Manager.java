package ru.sibsutis;

class Manager extends User implements CSV {
    private String level;

    public Manager(String name, String email, String level) {
        super(name, email);
        this.level = level;
    }

    public String getLevel() {
        return this.level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toCSV() {
        return getName() + ',' + getEmail() + ',' + getLevel();
    }

    @Override
    public void fromCSV(String str) {
        String[] data = new String[3];
        data = str.split(";");
        setName(data[0]);
        setEmail(data[1]);
        setLevel(data[2]);
    }
}