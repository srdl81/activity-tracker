package ams.labs.model;

public class MatchResultDTO {

    private String id;
    private String arbetsgivareId;
    private String organisationsnummer;
    private String arbetsgivarenamn;
    private IdNamn yrkesroll;
    private ErbjudenArbetsplats erbjudenArbetsplats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganisationsnummer() {
        return organisationsnummer;
    }

    public void setOrganisationsnummer(String organisationsnummer) {
        this.organisationsnummer = organisationsnummer;
    }

    public String getArbetsgivareId() {
        return arbetsgivareId;
    }

    public void setArbetsgivareId(String arbetsgivareId) {
        this.arbetsgivareId = arbetsgivareId;
    }

    public String getArbetsgivarenamn() {
        return arbetsgivarenamn;
    }

    public void setArbetsgivarenamn(String arbetsgivarenamn) {
        this.arbetsgivarenamn = arbetsgivarenamn;
    }

    public IdNamn getYrkesroll() {
        return yrkesroll;
    }

    public void setYrkesroll(IdNamn yrkesroll) {
        this.yrkesroll = yrkesroll;
    }

    public ErbjudenArbetsplats getErbjudenArbetsplats() {
        return erbjudenArbetsplats;
    }

    public void setErbjudenArbetsplats(ErbjudenArbetsplats erbjudenArbetsplats) {
        this.erbjudenArbetsplats = erbjudenArbetsplats;
    }

}
