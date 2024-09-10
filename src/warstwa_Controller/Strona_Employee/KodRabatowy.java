package warstwa_Controller.Strona_Employee;


import warstwa_Model.Strona_Employee.KodRabatowyDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;

@ManagedBean
@RequestScoped
public class KodRabatowy {

    private List<Object[]> listOfKodyRabatowe = new ArrayList<Object[]>();
    private List<HashMap<String, String>> listOfMap_KodyRabatowe = new ArrayList<>();
    private Integer selected_idkod_rabatowy_to_delete;
    private String Nazwa;
    private String Rabat;
    private String RabatProcent;

    @EJB
    KodRabatowyDAO kodRabatowyDAO;


    /**
     * Metoda inicjująca
     */
    @PostConstruct
    public void init(){
        listOfMap_KodyRabatowe.clear();
        uzupelnijListeMap_KodyRabatowe();
    }

    /** Baza */ public void dodajKodRabatowy(){
        kodRabatowyDAO.addKodRabatowy(Nazwa, Rabat, RabatProcent);
        Nazwa = "";
        Rabat = "";
        RabatProcent = "";
    }

    /**
     * Metoda usuwająca kod rabatowy
     * @param hashMap
     */
    public void usunKodRabatowy(HashMap<String, String> hashMap){
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        selected_idkod_rabatowy_to_delete = parseInt(local_hashMap.get("idkody_rabatowe"));
        kodRabatowyDAO.deleteKodRabatowy(selected_idkod_rabatowy_to_delete);
        init();
    }
    /** Test */ public HashMap usunKodRabatowy___TEST(HashMap<String, String> hashMap){
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        selected_idkod_rabatowy_to_delete = parseInt(local_hashMap.get("idkody_rabatowe"));
        local_hashMap.remove("idkody_rabatowe");
        return local_hashMap;
    }

    /**
     * Metoda uzupełniająca liste map w której przechowywane są kody rabatowe
     */
    /** Baza */ public void uzupelnijListeMap_KodyRabatowe(){
        listOfKodyRabatowe = kodRabatowyDAO.listaKodowRabatowych();

        for (Object[] element : listOfKodyRabatowe) {
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("idkody_rabatowe", String.valueOf(element[0]));
            mapa.put("nazwa", String.valueOf(element[1]));
            mapa.put("rabat", String.valueOf(element[2]));
            mapa.put("rabat_procent", String.valueOf(element[3]));

            listOfMap_KodyRabatowe.add(mapa);
        }
    }


    /**
     * Metoda wczytująca menu dodawania kody rabatowe
     */
    public void loadKodyRabatowe(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Employee/KodRabatowy.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Test */ public String loadKodyRabatowe___TEST(){
        return "/_Employee/KodRabatowy.xhtml";
    }

    /**
     * Metoda wczytująca menu dodawania kodów rabatowych
     */
    public void load_dodajKodyRabatowe(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Employee/dodajKodRabatowy.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Test */ public String load_dodajKodyRabatowe___TEST(){
        return "/_Employee/dodajKodRabatowy.xhtml";
    }

    /**
     * Gettery i Settery
     * @return
     */
    /*************************/
    public String getNazwa() {
        return Nazwa;
    }

    public void setNazwa(String nazwa) {
        Nazwa = nazwa;
    }

    public String getRabat() {
        return Rabat;
    }

    public void setRabat(String rabat) {
        Rabat = rabat;
    }

    public String getRabatProcent() {
        return RabatProcent;
    }

    public void setRabatProcent(String rabatProcent) {
        RabatProcent = rabatProcent;
    }

    public List<Object[]> getListOfKodyRabatowe() {
        return listOfKodyRabatowe;
    }

    public void setListOfKodyRabatowe(List<Object[]> listOfKodyRabatowe) {
        this.listOfKodyRabatowe = listOfKodyRabatowe;
    }

    public List<HashMap<String, String>> getListOfMap_KodyRabatowe() {
        return listOfMap_KodyRabatowe;
    }

    public void setListOfMap_KodyRabatowe(List<HashMap<String, String>> listOfMap_KodyRabatowe) {
        this.listOfMap_KodyRabatowe = listOfMap_KodyRabatowe;
    }
    /*************************/
}
