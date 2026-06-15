package model;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("platform")
@ApplicationScoped

public class Platform implements Serializable {

    private final List<Benutzer> benutzerListe = new ArrayList<>();

    public Platform() {
        benutzerListe.add(new Benutzer("fobius", "12345"));
        benutzerListe.add(new Benutzer("cleo",   "98765"));
        benutzerListe.add(new Benutzer("root",   "678910"));
    }

    public List<Benutzer> getBenutzerListe() {
        return benutzerListe;
    }
}