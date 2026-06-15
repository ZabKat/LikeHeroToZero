package web;

import model.Emission;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named("co2Bean")
@SessionScoped
public class Co2Bean implements Serializable {

    private final EmissionDao dao = new EmissionDao();
    private Emission currentEmission = new Emission();

    private String selectedCountry;
    private Emission latestEmission;

    public List<Emission> getEmissions() { return dao.findAll(); }
    public List<String> getAllCountries() { return dao.findAllCountries(); }

    public void searchLatest() {
        if (selectedCountry != null) {
            this.latestEmission = dao.findLatestByCountry(selectedCountry);
        }
    }

    public List<Integer> getYears() {
        List<Integer> years = new ArrayList<>();
        for (int i = 1990; i <= 2025; i++) {
            years.add(i);
        }
        return years;
    }

    public String save() {
        if (currentEmission.getId() == null) dao.create(currentEmission);
        else dao.update(currentEmission);
        currentEmission = new Emission();
        return "emissions_edit.xhtml?faces-redirect=true";
    }

    public String edit(Emission e) {
        this.currentEmission = e;
        return "emissions_edit.xhtml";
    }

    public String delete(Emission e) {
        dao.delete(e);
        return "emissions_edit.xhtml";
    }

    public Emission getCurrentEmission() { return currentEmission; }
    public void setCurrentEmission(Emission e) { this.currentEmission = e; }

    public String getSelectedCountry() { return selectedCountry; }
    public void setSelectedCountry(String s) { this.selectedCountry = s; }

    public Emission getLatestEmission() { return latestEmission; }
}