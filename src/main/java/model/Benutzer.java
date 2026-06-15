package model;

import java.io.Serializable;

public class Benutzer implements Serializable {

    private String name;
    private String passwort;

    public Benutzer() {
    }

    public Benutzer(String name, String passwort) {
        this.name = name;
        this.passwort = passwort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Benutzer) {
            Benutzer b = (Benutzer) obj;
            return b.getName().equals(this.name)
                    && b.getPasswort().equals(this.passwort);
        }
        return false;
    }
}