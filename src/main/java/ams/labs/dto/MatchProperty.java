package ams.labs.dto;

public class MatchProperty {
    private String id;
    private String namn;

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
        return "MatchProperty{" +
                "id='" + id + '\'' +
                ", namn='" + namn + '\'' +
                '}';
    }
}
