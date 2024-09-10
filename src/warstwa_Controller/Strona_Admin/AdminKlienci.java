package warstwa_Controller.Strona_Admin;

import warstwa_Model.Strona_Admin.KlienciDAO;

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
public class AdminKlienci {


    private List<Object[]> ListofKlienci = new ArrayList<Object[]>();
    private List<HashMap<String, String>> ListofMap_Klienci = new ArrayList<>();
    private List<Object[]> ListOfSelectedKlient = new ArrayList<Object[]>();
    private List<HashMap<String,String>> ListOfMap_SelectedKlient = new ArrayList<>();
    private String idOsoby;
    private String idKlienta;
    private String imieKl;
    private String nazwiskoKl;
    private String loginKl;
    private String hasloKl;
    private String emailKl;
    private String idOsobaSelected;





    @EJB
    private KlienciDAO klienciDAO;


    /**
     * Metoda inicjalizujaca liste klientów z warstwy modelu
     */
    @PostConstruct
    public void init() {

        ListofMap_Klienci.clear();
        uzupelnijListeMapZawartoscia_Klientow();
    }

    /**
     * Metoda przekierowujaca użytkownika do kolejnej strony
     */

    public void takeCurrentIdOsoba(HashMap<String, String> hashMap) {

        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idOsobaSelected = "jest puste";

        idOsobaSelected = local_hashMap.get("idOsoba");

        openSelectedKlient();
    }

    public void openSelectedKlient() {

        ListOfMap_SelectedKlient.clear();

        uzupelnijListeMap_SelectedKlient();

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/EditClients.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uzupelnijListeMap_SelectedKlient() {

        ListOfSelectedKlient = klienciDAO.getSelectedKlientforUpdate(Integer.parseInt(getIdOsobaSelected()));

        for (Object[] element : ListOfSelectedKlient) {
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("idKlientSelected" , String.valueOf(element[0]));
            mapa.put("ImieSelected" , String.valueOf(element[1]));
            mapa.put("NazwiskoSelected" , String.valueOf(element[2]));
            mapa.put("LoginSelected" , String.valueOf(element[3]));
            mapa.put("HasloSelected" , String.valueOf(element[4]));
            mapa.put("EmailSelected" , String.valueOf(element[5]));
            mapa.put("idOsobaSelected" , String.valueOf(element[6]));

            ListOfMap_SelectedKlient.add(mapa);
        }
    }
    public void openKlienci(){

        ListofMap_Klienci.clear();
        uzupelnijListeMapZawartoscia_Klientow();

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/Clients.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uzupelnijListeMapZawartoscia_Klientow() {

        ListofKlienci = klienciDAO.getKlienci();

        for (Object[] element : ListofKlienci) {
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("idKlient" , String.valueOf(element[0]));
            mapa.put("Imie" , String.valueOf(element[1]));
            mapa.put("Nazwisko" , String.valueOf(element[2]));
            mapa.put("Login" , String.valueOf(element[3]));
            mapa.put("Haslo" , String.valueOf(element[4]));
            mapa.put("Email" , String.valueOf(element[5]));
            mapa.put("idOsoba" , String.valueOf(element[6]));

            ListofMap_Klienci.add(mapa);
        }
    }

    public void usunPracownika(HashMap<String, String> hashMap) {

        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idKlienta = "jest puste";
        idKlienta = local_hashMap.get("idKlient");
        idOsoby = local_hashMap.get("idOsoba");

        klienciDAO.deleteClients(parseInt(idKlienta), parseInt(idOsoby));

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/Clients.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        init();
    }


    public void SaveClient(){


        klienciDAO.updateClients(parseInt(idOsobaSelected), loginKl, getSHA256(hasloKl), imieKl, nazwiskoKl, emailKl);

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/EditClients.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListOfMap_SelectedKlient.clear();
        uzupelnijListeMap_SelectedKlient();
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

    public List<Object[]> getListofKlienci() {
        return ListofKlienci;
    }

    public void setListofKlienci(List<Object[]> listofKlienci) {
        ListofKlienci = listofKlienci;
    }

    public List<HashMap<String, String>> getListofMap_Klienci() {
        return ListofMap_Klienci;
    }

    public void setListofMap_Klienci(List<HashMap<String, String>> listofMap_Klienci) {
        ListofMap_Klienci = listofMap_Klienci;
    }

    public List<Object[]> getListOfSelectedKlient() {
        return ListOfSelectedKlient;
    }

    public void setListOfSelectedKlient(List<Object[]> listOfSelectedKlient) {
        ListOfSelectedKlient = listOfSelectedKlient;
    }

    public List<HashMap<String, String>> getListOfMap_SelectedKlient() {
        return ListOfMap_SelectedKlient;
    }

    public void setListOfMap_SelectedKlient(List<HashMap<String, String>> listOfMap_SelectedKlient) {
        ListOfMap_SelectedKlient = listOfMap_SelectedKlient;
    }

    public String getIdOsoby() {
        return idOsoby;
    }

    public void setIdOsoby(String idOsoby) {
        this.idOsoby = idOsoby;
    }

    public String getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(String idKlienta) {
        this.idKlienta = idKlienta;
    }

    public String getImieKl() {
        return imieKl;
    }

    public void setImieKl(String imieKl) {
        this.imieKl = imieKl;
    }

    public String getNazwiskoKl() {
        return nazwiskoKl;
    }

    public void setNazwiskoKl(String nazwiskoKl) {
        this.nazwiskoKl = nazwiskoKl;
    }

    public String getLoginKl() {
        return loginKl;
    }

    public void setLoginKl(String loginKl) {
        this.loginKl = loginKl;
    }

    public String getHasloKl() {
        return hasloKl;
    }

    public void setHasloKl(String hasloKl) {
        this.hasloKl = hasloKl;
    }

    public String getEmailKl() {
        return emailKl;
    }

    public void setEmailKl(String emailKl) {
        this.emailKl = emailKl;
    }

    public String getIdOsobaSelected() {
        return idOsobaSelected;
    }

    public void setIdOsobaSelected(String idOsobaSelected) {
        this.idOsobaSelected = idOsobaSelected;
    }
}
