package warstwa_Controller.Strona_Admin;

import warstwa_Model.Strona_Admin.PracownicyDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;


@ManagedBean
@SessionScoped
/**
 * Klasa odpowiedzialna za kontrolowanie pracowników
 * w zakładce admina
 */
public class AdminPracownicy {

    private List<Object[]> ListOfPracownicy = new ArrayList<Object[]>();
    private List<HashMap<String, String>> ListOfmap_Pracownicy =new ArrayList<>();
    private String idPracownika;
    private String idOsoby;
    private String login, haslo, imie, nazwisko, stanowisko;
    private Integer pensja;

    @EJB
    PracownicyDAO pracownicyDAO;

    @PostConstruct
    public void init(){
        ListOfmap_Pracownicy.clear();
        uzupelnijListeMapZawartoscia_Pracownikami();
    }



    /** Test */ public String loadFiles___TEST(){
        return "/_Admin/Workers.xhtml";
    }

    /**
     * Metoda przekierowania admina do strony dodawania pracownika
     */
    public void loadAddWorker() {

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/AddWorkers.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda konwertująca na SHA256
     * @param data
     * @return
     */
    public static String getSHA256(String data){
        StringBuilder sb = new StringBuilder();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data.getBytes());
            byte[] byteData = md.digest();

            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xff) + 0x100, 16).substring(1));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void  uzupelnijListeMapZawartoscia_Pracownikami() {

        ListOfPracownicy = pracownicyDAO.getPracownicy();

        for (Object[] element : ListOfPracownicy){
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("idPracownik", String.valueOf(element[0]));
            mapa.put("Imie", String.valueOf(element[1]));
            mapa.put("Nazwisko", String.valueOf(element[2]));
            mapa.put("Login", String.valueOf(element[3]));
            mapa.put("Haslo", String.valueOf(element[4]));
            mapa.put("Stanowisko", String.valueOf(element[5]));
            mapa.put("Pensja", String.valueOf(element[6]));
            mapa.put("Osoba_idOsoba", String.valueOf(element[7]));

            ListOfmap_Pracownicy.add(mapa);
        }
    }

    public void deletePracownik(HashMap<String, String> hashMap){

        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idOsoby = "jest puste";
        idOsoby = local_hashMap.get("Osoba_idOsoba");
        idPracownika = local_hashMap.get("idPracownik");

        pracownicyDAO.deletePracownikZapytanie(parseInt(idOsoby), parseInt(idPracownika));

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/Workers.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        init();
    }

    public void openPracownicy(){
        ListOfmap_Pracownicy.clear();
        uzupelnijListeMapZawartoscia_Pracownikami();

        FacesContext context = FacesContext.getCurrentInstance();
        try{
            context.getExternalContext().redirect("/_Admin/Workers.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda dodaje nowego pracownika do bazy
     */
    /** Baza */ public void addWorker(){
        pracownicyDAO.addWorker_part1(pracownicyDAO.getIdToAddNewPerson()+1, login, getSHA256(haslo), imie, nazwisko);
        pracownicyDAO.addWorker_part2(pracownicyDAO.getIdToAddNewPerson());
        pracownicyDAO.addWorker_part3(pracownicyDAO.getIdToAddNewKlient()+1, stanowisko, pensja, pracownicyDAO.getIdToAddNewPerson());
        login = "";
        haslo = "";
        imie = "";
        nazwisko = "";
        stanowisko = "";
        pensja = null;
        openPracownicy();
    }




























    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public Integer getPensja() {
        return pensja;
    }

    public void setPensja(Integer pensja) {
        this.pensja = pensja;
    }

    public List<Object[]> getListOfPracownicy() {
        return ListOfPracownicy;
    }

    public void setListOfPracownicy(List<Object[]> listOfPracownicy) {
        ListOfPracownicy = listOfPracownicy;
    }

    public List<HashMap<String, String>> getListOfmap_Pracownicy() {
        return ListOfmap_Pracownicy;
    }

    public void setListOfmap_Pracownicy(List<HashMap<String, String>> listOfmap_Pracownicy) {
        ListOfmap_Pracownicy = listOfmap_Pracownicy;
    }

    public String getIdPracownika() {
        return idPracownika;
    }

    public void setIdPracownika(String idPracownika) {
        this.idPracownika = idPracownika;
    }

    public String getIdOsoby() {
        return idOsoby;
    }

    public void setIdOsoby(String idOsoby) {
        this.idOsoby = idOsoby;
    }


}
