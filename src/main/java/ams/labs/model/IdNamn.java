package ams.labs.model;

public class IdNamn {
    String id;
    String namn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    @Override
    public String toString() {
        return "IdNamn{" +
                "id='" + id + '\'' +
                ", namn='" + namn + '\'' +
                '}';
    }
}
