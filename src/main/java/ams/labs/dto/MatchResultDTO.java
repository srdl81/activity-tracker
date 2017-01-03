package ams.labs.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MatchResultDTO {

    @NotNull
    @Size(min=6, max=15)
    private String id;

    @NotNull
    @Size(min=6, max=12)
    private String arbetsgivareId;

    @NotNull
    @Size(min=8, max=12)
    private String organisationsnummer;

    @NotNull
    @Size(min=1, max=50)
    private String arbetsgivarenamn;

    @NotNull
    private IdNamn yrkesroll;

    @NotNull
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

    @Override
    public String toString() {
        return "MatchResultDTO{" +
                "id='" + id + '\'' +
                ", arbetsgivareId='" + arbetsgivareId + '\'' +
                ", organisationsnummer='" + organisationsnummer + '\'' +
                ", arbetsgivarenamn='" + arbetsgivarenamn + '\'' +
                ", yrkesroll=" + yrkesroll +
                ", erbjudenArbetsplats=" + erbjudenArbetsplats +
                '}';
    }
}
