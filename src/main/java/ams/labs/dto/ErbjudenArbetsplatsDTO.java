package ams.labs.dto;


public class ErbjudenArbetsplatsDTO {

    PropertyDTO kommun;

    public PropertyDTO getKommun() {
        return kommun;
    }

    public void setKommun(PropertyDTO kommun) {
        this.kommun = kommun;
    }

    @Override
    public String toString() {
        return "ErbjudenArbetsplatsDTO{" +
                "kommun=" + kommun +
                '}';
    }
}
