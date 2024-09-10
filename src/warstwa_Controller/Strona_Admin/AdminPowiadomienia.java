package warstwa_Controller.Strona_Admin;

import DataBase_AutoGenerated.Osoba;
import DataBase_AutoGenerated.Powiadomienia;
import warstwa_Model.Strona_Admin.PowiadomieniaDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;


/**
 * Klasa odpowiedzialna za kontrolowanie powiadomien
 * w zakładce admina
 */
@ManagedBean
@SessionScoped
public class AdminPowiadomienia {

    private List<Osoba> listaOsob;
    private List<Powiadomienia> listaPowiadomien;
    public int numberOfNotifications = 0;
    private String idOsoby;
    public int id;
    private int idPow;
    private int odKogo;
    private int doKogo;
    private String trescPow;
    private String tematPow;


    @EJB
    private PowiadomieniaDAO powiadomieniaDAO;


    /**
     * Metoda uzywana jako konstruktor,
     * przypisuje wartosci do zmiennych z modelu
     */
    @PostConstruct
    public void init() {
        id = powiadomieniaDAO.getIdOfNotifications().get(0);
        listaPowiadomien = powiadomieniaDAO.getListOfNotifications(id);
        idOsoby = powiadomieniaDAO.getIdOfPersons(id);
        listaOsob = powiadomieniaDAO.getListOfPersons(Integer.parseInt(idOsoby));
        numberOfNotifications = powiadomieniaDAO.getIdOfNotifications().size();

    }

    /**
     * Metoda zwiekszajaca indeks listy o 1 po nacisnieciu przycisku
     */
    /** Baza */ public void nextID(){

        listaPowiadomien = powiadomieniaDAO.getListOfNotifications(id);
        listaOsob = powiadomieniaDAO.getListOfPersons(Integer.parseInt(idOsoby));
        numberOfNotifications = powiadomieniaDAO.getIdOfNotifications().size();

        if(id >= numberOfNotifications){
            id = powiadomieniaDAO.getIdOfNotifications().get(0);
        }else{
            id++;
        }

        idOsoby = powiadomieniaDAO.getIdOfPersons(id);
    }

    /**
     * Metoda usuwajaca powiadomienie
     */
    /** Baza */ public void deleteNotification(){

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/Notifications.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        powiadomieniaDAO.deleteNotification(id);
    }

    /**
     * Metoda wysylajaca powiadomienie do bazy
     */
    /** Baza */ public void SendNewNotification(){

        powiadomieniaDAO.createNotification(idPow, tematPow, trescPow, odKogo, doKogo);

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/Notifications.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda tworzaca nowe powiadomienie
     */
    public void NewNotification(){

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/NewNotification.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Test */ public String NewNotification___TEST(){
        return "/_Admin/NewNotification.xhtml";
    }

    /**
     * Metoda przekierowujaca użytkownika do kolejnej strony
     */
    public void loadFiles(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/Notifications.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Test */ public String loadFiles___TEST(){
        return "/_Admin/Notifications.xhtml";
    }

    /**
     * Gettery i Settery
     * @return
     */
    /*************************/
    public int getNumberOfNotifications() {
        return numberOfNotifications;
    }

    public List<Osoba> getListaOsob() {
        return listaOsob;
    }

    public List<Powiadomienia> getListaPowiadomien() {
        return listaPowiadomien;
    }

    public int getId() {
        return id;
    }

    public int getOdKogo() {
        return odKogo;
    }

    public void setOdKogo(int odKogo) {
        this.odKogo = odKogo;
    }

    public int getDoKogo() {
        return doKogo;
    }

    public void setDoKogo(int doKogo) {
        this.doKogo = doKogo;
    }

    public String getTrescPow() {
        return trescPow;
    }

    public void setTrescPow(String trescPow) {
        this.trescPow = trescPow;
    }

    public String getTematPow() {
        return tematPow;
    }

    public void setTematPow(String tematPow) {
        this.tematPow = tematPow;
    }

    public int getIdPow() {
        return idPow;
    }

    public void setIdPow(int idPow) {
        this.idPow = idPow;
    }
    /*************************/
}
