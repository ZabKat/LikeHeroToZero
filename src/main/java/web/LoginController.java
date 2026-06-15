package web;

import model.Benutzer;
import model.Platform;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("loginController")
@SessionScoped

public class LoginController implements Serializable {

    @Inject
    private Platform platform;

    private Benutzer benutzer = new Benutzer();
    private Benutzer loggedInUser = null;

    public Benutzer getBenutzer() {
        return benutzer;
    }

    public String login() {
        for (Benutzer b : platform.getBenutzerListe()) {
            if (b.equals(benutzer)) {
                this.loggedInUser = b;
                return "emissions_edit.xhtml?faces-redirect=true";
            }
        }

        FacesMessage msg = new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "Login fehlgeschlagen",
                "Benutzername oder Passwort ist falsch."
        );
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public boolean isAuthorized() {
        return loggedInUser != null;
    }

    public void setLoggedInUser(Benutzer user) {
        this.loggedInUser = user;
    }
}