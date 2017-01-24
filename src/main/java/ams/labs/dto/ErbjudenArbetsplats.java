package ams.labs.dto;


public class ErbjudenArbetsplats {

    MatchProperty kommun;

    public MatchProperty getKommun() {
        return kommun;
    }

    public void setKommun(MatchProperty kommun) {
        this.kommun = kommun;
    }

    @Override
    public String toString() {
        return "ErbjudenArbetsplats{" +
                "kommun=" + kommun +
                '}';
    }
}
