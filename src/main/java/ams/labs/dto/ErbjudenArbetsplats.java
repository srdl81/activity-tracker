package ams.labs.dto;


public class ErbjudenArbetsplats {

    IdNamn kommun;

    public IdNamn getKommun() {
        return kommun;
    }

    public void setKommun(IdNamn kommun) {
        this.kommun = kommun;
    }

    @Override
    public String toString() {
        return "ErbjudenArbetsplats{" +
                "kommun=" + kommun +
                '}';
    }
}
