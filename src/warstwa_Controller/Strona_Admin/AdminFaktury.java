package warstwa_Controller.Strona_Admin;


import warstwa_Model.Strona_Admin.FakturyDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ManagedBean
@SessionScoped
public class AdminFaktury {

    private List<Object[]> listOfFaktury = new ArrayList<Object[]>();
    private List<HashMap<String, String>> listOfmap_Faktury = new ArrayList<>();


    @EJB
    FakturyDAO fakturyDAO;


    /**
     * Metoda inicjująca
     */
    @PostConstruct
    public void init(){

    }

    /**
     * Metoda uzupełniająca liste map, odpowiada za wyświetlaną historie tranzakcji
     */
    /** Lista Map */ public void uzupelnijListeMapZawartoscia__Faktury() {
        // przypisanie do pustej listy, listy obiektów z selecta

        listOfFaktury = fakturyDAO.getFakturyForKlient();

        for (Object[] element : listOfFaktury) {
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("idKlient_FK", String.valueOf(element[0]));
            mapa.put("Nazwa", String.valueOf(element[1]));
            mapa.put("Cena", String.valueOf(element[2]));
            mapa.put("Ilosc_produktow", String.valueOf(element[3]));
            mapa.put("Data_sprzedazy", String.valueOf(element[4]));
            mapa.put("Imie", String.valueOf(element[5]));
            mapa.put("Nazwisko", String.valueOf(element[6]));
            mapa.put("Miasto", String.valueOf(element[7]));
            mapa.put("Ulica", String.valueOf(element[8]));
            mapa.put("NrDomu", String.valueOf(element[9]));
            mapa.put("KodPocztowy", String.valueOf(element[10]));


            listOfmap_Faktury.add(mapa);
        }
    }

    /**
     * Metoda przekierowująca do faktur
     */
    public void openFaktury() {
        listOfmap_Faktury.clear();
        uzupelnijListeMapZawartoscia__Faktury();

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/FakturyAdmin.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Test */public String openFaktury___TEST() {
        return "/_Admin/FakturyAdmin.xhtml";
    }


    /**
     * Gettery i Settery
     * @return
     */
    /*************************/
    public List<Object[]> getListOfFaktury() {
        return listOfFaktury;
    }

    public void setListOfFaktury(List<Object[]> listOfFaktury) {
        this.listOfFaktury = listOfFaktury;
    }

    public List<HashMap<String, String>> getListOfmap_Faktury() {
        return listOfmap_Faktury;
    }

    public void setListOfmap_Faktury(List<HashMap<String, String>> listOfmap_Faktury) {
        this.listOfmap_Faktury = listOfmap_Faktury;
    }
    /*************************/
}
