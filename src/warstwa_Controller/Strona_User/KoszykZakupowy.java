package warstwa_Controller.Strona_User;


import warstwa_Model.Strona_User.KoszykZakupowyDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static java.lang.Integer.parseInt;
import static oracle.jrockit.jfr.events.Bits.intValue;

@ManagedBean
@SessionScoped
public class KoszykZakupowy {

    boolean flaga;
    private String miasto;
    private String ulica;
    private String nr_domu;
    private String kod_pocztowy;
    private String cena_FAKTURA;
    private String ilosc_FAKTURA;
    private Integer cena_przed, cena_po;
    private Double rabatt;
    private String idKoszyk_SELECTED;
    private String idKoszyk_SELECTED_Rabat;
    private String idKoszyk_SELECTED_Usun;
    private String idKoszyk_SELECTED_Edytuj;
    private String idKoszyk_SELECTED_Realizuj;
    private String idkoszyk_produkty;
    private String nazwa_kodu_rabatowego;
    private Oferta oferta = new Oferta();
    private String wersja;
    private List<Object[]> listOfProductsIn_KoszykProdukty = new ArrayList<Object[]>(); // lista obiektów przechowująca selecta ktory zawiera liste produktow z koszyka
    private List<Object[]> listOfProductsIn_KoszykZamowienia = new ArrayList<Object[]>();
    private List<Object[]> listOfFaktury = new ArrayList<Object[]>();
    private List<HashMap<String, String>> listOfmap_KoszykProdukty = new ArrayList<>(); // lista map przechowująca pojedyńcze rekordy z selecta
    private List<HashMap<String, String>> listOfmap_KoszykZamowienia = new ArrayList<>();
    private List<HashMap<String, String>> listOfmap_Faktury = new ArrayList<>();

    @EJB
    KoszykZakupowyDAO koszykZakupowyDAO;


    @PostConstruct
    public void init()
    {
        listOfmap_KoszykZamowienia.clear();
        uzupelnijListeMapZawartoscia__Koszyk_Zamowienia();
        //wersja = FacesContext.class.getPackage().getImplementationVersion(); // sprawdza wersje JSF
        refreshSizeOf__Koszyk_Produkty();
        refreshPriceOf__Koszyk_Produkty();
        koszykZakupowyDAO.nullSecurity_CenaKoncowa();
    }


    /**
     * Dodaje produkt do koszyka
     */
    /** Baza */public void addProductsTo_KoszykProdukty() {
        int idKoszyK_FK = aktualnie_edytowany_koszyk(); // prosze mnie nie pytać o to...
        int idProdukty_FK = oferta.getId(); // pobieram ID aktualnego produktu przy którym został wciśnięty klawisz dodajDoKoszyka
        koszykZakupowyDAO.addKoszykProdukty(idKoszyK_FK, idProdukty_FK); // dodaje produkt do koszyka o numerze ID=1, id koszyka jest statyczne
        refreshSizeOf__Koszyk_Produkty();
        refreshPriceOf__Koszyk_Produkty();
        koszykZakupowyDAO.zerujRabaty(idKoszyK_FK);
    }

    /** Baza */public void addNewKoszyk(){
        koszykZakupowyDAO.addKoszykZamowienie(parseInt(getCurrentUserIDByUsername()));
        init();
    }

    /**
     * Sprawdza który koszyk jest aktualnie edytowany
     *
     * @return
     */
    /** Baza */public int aktualnie_edytowany_koszyk() {
        int idKoszyK_FK = parseInt(String.valueOf(koszykZakupowyDAO.getCurrentIdOfKoszykZakupowy(parseInt(getCurrentUserIDByUsername()))).replace("[", "").replace("]", ""));

        if(flaga && (idKoszyK_FK != parseInt(idKoszyk_SELECTED_Edytuj)))
        {
            idKoszyK_FK = parseInt(idKoszyk_SELECTED_Edytuj);
        }
        return idKoszyK_FK;
    }

    /**
     * Zwraca id klienta który jest aktualnie zalogowany
     *
     * @return
     */
    /** Baza */public String getCurrentUserIDByUsername() {
        String currentUsername = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return String.valueOf(koszykZakupowyDAO.getCurrentUserID(currentUsername)).replace("[", "").replace("]", "");

    }

    /**
     * Otwiera liste koszyków klienta
     */
    public void openListaKoszykow()
    {
        listOfmap_KoszykZamowienia.clear();
        uzupelnijListeMapZawartoscia__Koszyk_Zamowienia();

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_User/ListaKoszykow.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Otwiera wybrany przez klienta koszyk
     */
    public void openSelectedKoszyk() {
        listOfmap_KoszykProdukty.clear();
        uzupelnijListeMapZawartoscia__Koszyk_Produkty();

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_User/ProductsInSelectedKoszyk.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFaktury() {
        listOfmap_Faktury.clear();
        uzupelnijListeMapZawartoscia__Faktury();

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_User/Faktury.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Otwiera realizacje zamówienia
     */
    public void openRealizacjaZamowienia(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idKoszyk_SELECTED_Realizuj = "jest pusty";

        idKoszyk_SELECTED_Realizuj = local_hashMap.get("idKoszyk");
        cena_FAKTURA = local_hashMap.get("Cena_rabat");
        ilosc_FAKTURA = local_hashMap.get("Ilosc");

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_User/RealizacjaZamowienia.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zczytuje wybrany przez nas koszyk i otwiera jego zawartość
     *
     * @param hashMap
     */
    public void takeCurrentObjectMap(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idKoszyk_SELECTED = "jest pusty";

        idKoszyk_SELECTED = local_hashMap.get("idKoszyk");

        openSelectedKoszyk();
    }
    /** Test */ public String takeCurrentObjectMap___TEST(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idKoszyk_SELECTED = "jest pusty";

        idKoszyk_SELECTED = local_hashMap.get("idKoszyk");
        return idKoszyk_SELECTED;
    }

    /** Baza */ public void getCurrentIdKoszykFromSelectedRow(HashMap<String, String> hashMap) {
        String tmp;
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idKoszyk_SELECTED_Rabat = "jest pusty";

        idKoszyk_SELECTED_Rabat = local_hashMap.get("idKoszyk");

        /**
         * naliczanie rabatów
         */
        tmp = getNazwa_kodu_rabatowego();
        int cena_przed_rabatem = 0, cena_po_rabacie;
        double rabat = 0;
        if (!koszykZakupowyDAO.getSelectedRabat(tmp).isEmpty()) {
            rabat = Double.parseDouble(koszykZakupowyDAO.getSelectedRabat(tmp).toString().replace("[", "").replace("]", ""));
        }
        if (!koszykZakupowyDAO.getSelectedCenaKoncowa(parseInt(idKoszyk_SELECTED_Rabat)).isEmpty()) {
            cena_przed_rabatem = parseInt(String.valueOf(koszykZakupowyDAO.getSelectedCenaKoncowa(parseInt(idKoszyk_SELECTED_Rabat))).replace("[", "").replace("]", ""));
        }

        cena_po_rabacie = intValue(cena_przed_rabatem * rabat);
        if (cena_po_rabacie == 0)
            cena_po_rabacie = cena_przed_rabatem;

        cena_przed = cena_przed_rabatem;
        cena_po = cena_po_rabacie;
        rabatt = rabat;

        refreshDiscount__Koszyk_Zamowienie();
        refreshPriceAfterDiscount__Koszyk_Zamowienie(cena_po_rabacie);
        init();
    }

    /**
     * Przypisuje liście koszyków kolumny aby potem można było korzystać z nich na froncie, użyta jest do tego lista map
     */
    /** Lista Map */ public void uzupelnijListeMapZawartoscia__Koszyk_Zamowienia() {
        // przypisanie do pustej listy, listy obiektów z selecta
        int id_aktualnie_zalogowanego_klienta = Integer.parseInt(getCurrentUserIDByUsername());
        listOfProductsIn_KoszykZamowienia = koszykZakupowyDAO.getCurrentListOfKoszykZakupowy(id_aktualnie_zalogowanego_klienta);

        for (Object[] element : listOfProductsIn_KoszykZamowienia) {
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("idKoszyk", String.valueOf(element[0]));
            mapa.put("Ilosc", String.valueOf(element[1]));
            mapa.put("Cena_koncowa", String.valueOf(element[2]));
            mapa.put("Rabat", String.valueOf(element[3]));
            mapa.put("Status", String.valueOf(element[4]));
            mapa.put("Cena_rabat", String.valueOf(element[5]));

            listOfmap_KoszykZamowienia.add(mapa);
        }
    }

    /**
     * Przypisuje koszykowi kolumny aby potem można było korzystać z nich na froncie, użyta jest do tego lista map
     */
    /** Lista Map */ public void uzupelnijListeMapZawartoscia__Koszyk_Produkty() {
        // przypisanie do pustej listy, listy obiektów z selecta
        int id_aktualnie_zalogowanego_klienta = Integer.parseInt(getCurrentUserIDByUsername());
        listOfProductsIn_KoszykProdukty = koszykZakupowyDAO.getListOfProductsIn_SelectedKoszyk(Integer.parseInt(getIdKoszyk_SELECTED()));

        for (Object[] element : listOfProductsIn_KoszykProdukty) {
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("nazwa", String.valueOf(element[0]));
            mapa.put("cena", String.valueOf(element[1]));
            mapa.put("producent", String.valueOf(element[2]));
            mapa.put("idkoszyk_produkty", String.valueOf(element[3]));

            listOfmap_KoszykProdukty.add(mapa);
        }
    }

    /** Lista Map */ public void uzupelnijListeMapZawartoscia__Faktury() {
        // przypisanie do pustej listy, listy obiektów z selecta
        int idOsoba = parseInt((String.valueOf(koszykZakupowyDAO.getCurrentOsobaID(parseInt(getCurrentUserIDByUsername())))).replace("[", "").replace("]", ""));
        int idKlient_FK = parseInt(getCurrentUserIDByUsername());

        listOfFaktury = koszykZakupowyDAO.getFakturyForKlient(idOsoba,idKlient_FK);

        for (Object[] element : listOfFaktury) {
            HashMap<String, String> mapa = new HashMap<>();
            mapa.put("Nazwa", String.valueOf(element[0]));
            mapa.put("Cena", String.valueOf(element[1]));
            mapa.put("Ilosc_produktow", String.valueOf(element[2]));
            mapa.put("Data_sprzedazy", String.valueOf(element[3]));
            mapa.put("Imie", String.valueOf(element[4]));
            mapa.put("Nazwisko", String.valueOf(element[5]));
            mapa.put("Miasto", String.valueOf(element[6]));
            mapa.put("Ulica", String.valueOf(element[7]));
            mapa.put("NrDomu", String.valueOf(element[8]));
            mapa.put("KodPocztowy", String.valueOf(element[9]));
            mapa.put("Email", String.valueOf(element[10]));
            mapa.put("Control", String.valueOf(element[11]));


            listOfmap_Faktury.add(mapa);
        }
    }

    /** Baza */ public void refreshSizeOf__Koszyk_Produkty() {
        koszykZakupowyDAO.addIloscProduktow__Koszyk_Zamowienie(aktualnie_edytowany_koszyk());
    }

    /** Baza */ public void refreshPriceOf__Koszyk_Produkty() {
        koszykZakupowyDAO.addCenaKoncowa__Koszyk_Zamowienie(aktualnie_edytowany_koszyk());
    }

    /** Baza */ public void refreshDiscount__Koszyk_Zamowienie() {
        koszykZakupowyDAO.addRabat__Koszyk_Zamowienia(nazwa_kodu_rabatowego, parseInt(idKoszyk_SELECTED_Rabat));
    }

    /** Baza */ public void refreshPriceAfterDiscount__Koszyk_Zamowienie(int cena_po_rabacie) {
        koszykZakupowyDAO.addCenaPoRabacie(cena_po_rabacie, parseInt(idKoszyk_SELECTED_Rabat));
    }

    /**
     * Odświeża finalną cene z podglądu w prawym górnym rogu
     * @return
     */
    public String refreshFinalPriceOnScreen() {
        String finalPriceOnScreen = String.valueOf(koszykZakupowyDAO.getSelectedCenaKoncowa(aktualnie_edytowany_koszyk())).replace("[", "").replace("]", "");

        if(finalPriceOnScreen.equals("null") || finalPriceOnScreen.equals("0") || finalPriceOnScreen.isEmpty())
        {
            return "0.00";
        }
        else
            return finalPriceOnScreen;
    }
    /** Test */ public String refreshFinalPriceOnScreen___TEST(int cena_koncowa) {
        String finalPriceOnScreen = String.valueOf(cena_koncowa);

        if(finalPriceOnScreen.equals("null") || finalPriceOnScreen.equals("0") || finalPriceOnScreen.isEmpty())
        {
            return "0.00";
        }
        else
            return finalPriceOnScreen;
    }

    /**
     * Metoda odpowiadająca za pobieranie bieżącej hashmapy z listy map, oraz wyciągnięcie z niej idkoszyk_produkty i usunięcie go
     * @param hashMap
     */
    public void getCurrentIdKoszykFKFromSelectedRow(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idkoszyk_produkty = "jest pusty";

        idkoszyk_produkty = local_hashMap.get("idkoszyk_produkty");

        koszykZakupowyDAO.deleteProduktFrom__Koszyk_Produkty(parseInt(idkoszyk_produkty));


        listOfmap_KoszykProdukty.clear();
        uzupelnijListeMapZawartoscia__Koszyk_Produkty();
        listOfmap_KoszykZamowienia.clear();
        uzupelnijListeMapZawartoscia__Koszyk_Zamowienia();
        koszykZakupowyDAO.addIloscProduktow__Koszyk_Zamowienie(parseInt(idKoszyk_SELECTED));
        koszykZakupowyDAO.addCenaKoncowa__Koszyk_Zamowienie(parseInt(idKoszyk_SELECTED));
        koszykZakupowyDAO.zerujRabaty(parseInt(idKoszyk_SELECTED));
    }
    /** Test */ public HashMap getCurrentIdKoszykFKFromSelectedRow___TEST(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idkoszyk_produkty = "jest pusty";

        idkoszyk_produkty = local_hashMap.get("idkoszyk_produkty");
        local_hashMap.remove("idkoszyk_produkty");
        return local_hashMap;
    }

    /**
     * Metoda odpowiadająca za pobieranie bieżącej hashmapy z listy map, oraz wyciągniecie z niej idKoszyka i usunięcie go
     * @param hashMap
     */
    public void getCurrentIdKoszykDeleteFromSelectedRow(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idKoszyk_SELECTED_Usun = "jest pusty";

        idKoszyk_SELECTED_Usun = local_hashMap.get("idKoszyk");

        int ilosc_koszykow_posiadana_przez_aktualnie_zalogowanego_uzytkownika = parseInt((koszykZakupowyDAO.getNumberOf__Koszyk_Zamowienie(parseInt(getCurrentUserIDByUsername()))).toString().replace("[", "").replace("]", ""));

        if(ilosc_koszykow_posiadana_przez_aktualnie_zalogowanego_uzytkownika > 1)
        {
            if(parseInt(idKoszyk_SELECTED_Usun) == aktualnie_edytowany_koszyk())
            {
                koszykZakupowyDAO.deleteKoszykFrom__Koszyk_Zamowienie(parseInt(idKoszyk_SELECTED_Usun));
                String tmp = String.valueOf(koszykZakupowyDAO.getCurrentIdOfKoszykZakupowy(parseInt(getCurrentUserIDByUsername()))).replace("[", "").replace("]", "");
                idKoszyk_SELECTED_Edytuj = tmp;
            }
            else{
                koszykZakupowyDAO.deleteKoszykFrom__Koszyk_Zamowienie(parseInt(idKoszyk_SELECTED_Usun));
            }

        }

        else{
            koszykZakupowyDAO.zerujKoszykZamowienie(parseInt(idKoszyk_SELECTED_Usun));
        }

        init();
    }
    /** Test */ public HashMap getCurrentIdKoszykDeleteFromSelectedRow___TEST(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idKoszyk_SELECTED_Usun = "jest pusty";

        idKoszyk_SELECTED_Usun = local_hashMap.get("idKoszyk");
        local_hashMap.remove("idKoszyk");
        return local_hashMap;
    }

    /**
     * Metoda odpowiadająca za pobieranie bieżącej hashmapy z listy map, oraz wyciągnięcie z niej idKoszyka
     * @param hashMap
     */
    public void getIdKoszykFromSelectedRowToEdit(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        if (local_hashMap.isEmpty())
            idKoszyk_SELECTED_Edytuj = "jest pusty";

        idKoszyk_SELECTED_Edytuj = local_hashMap.get("idKoszyk");
        flaga = true;
    }

    public void przeslijDaneDoDotPaya(HashMap<String, String> hashMap) {
        HashMap<String, String> local_hashMap = new HashMap<>();
        local_hashMap = hashMap;

        String nazwa,cena,ilosc,data,imie,nazwisko,miasto,ulica,numer_domu,kod_pocztowy,email,control;

        nazwa = local_hashMap.get("Nazwa");
        cena = local_hashMap.get("Cena");
        ilosc = local_hashMap.get("Ilosc_produktow");
        data = local_hashMap.get("Data_sprzedazy");
        imie = local_hashMap.get("Imie");
        nazwisko = local_hashMap.get("Nazwisko");
        miasto = local_hashMap.get("Miasto");
        ulica = local_hashMap.get("Ulica");
        numer_domu = local_hashMap.get("NrDomu");
        kod_pocztowy = local_hashMap.get("KodPocztowy");
        email = local_hashMap.get("Email");
        control = local_hashMap.get("Control");

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_User/FakturyController.jsp?nazwa="+nazwa+"&cena="+cena+"&ilosc="+ilosc+"&data="+data+"&imie="+imie+"&nazwisko="+nazwisko+"&miasto="+miasto+"&ulica="+ulica+"&numer_domu="+numer_domu+"&kod_pocztowy="+kod_pocztowy+"&email="+email+"&control="+control);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Metoda zatwierdzajaca realizacje zamowienia
     */
    public void zatwierdzRealizacje(){
        int idOsoba = parseInt((String.valueOf(koszykZakupowyDAO.getCurrentOsobaID(parseInt(getCurrentUserIDByUsername())))).replace("[", "").replace("]", ""));

        koszykZakupowyDAO.addDaneDoFaktury(idOsoba ,miasto, ulica, nr_domu, kod_pocztowy);
        koszykZakupowyDAO.addFaktura(now(), cena_FAKTURA, ilosc_FAKTURA, parseInt(getCurrentUserIDByUsername()));
        miasto = "";
        ulica = "";
        nr_domu = "";
        kod_pocztowy = "";
        openFaktury();
    }

    /**
     * Pole odpowiedzialne za przechowywanie daty i godziny
     */
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    /**
     * Metoda zwraca aktualny czas i date w postaci Stringa
     * @return
     */
    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    /**
     * Gettery i Settery
     * @return
     */
    /*************************/
    public List<HashMap<String, String>> getListOfmap_KoszykProdukty() {
        return listOfmap_KoszykProdukty;
    }

    public void setListOfmap_KoszykProdukty(List<HashMap<String, String>> listOfmap_KoszykProdukty) {
        this.listOfmap_KoszykProdukty = listOfmap_KoszykProdukty;
    }

    public List<HashMap<String, String>> getListOfmap_KoszykZamowienia() {
        return listOfmap_KoszykZamowienia;
    }

    public void setListOfmap_KoszykZamowienia(List<HashMap<String, String>> listOfmap_KoszykZamowienia) {
        this.listOfmap_KoszykZamowienia = listOfmap_KoszykZamowienia;
    }

    public String getIdKoszyk_SELECTED() {
        return idKoszyk_SELECTED;
    }

    public void setIdKoszyk_SELECTED(String idKoszyk_SELECTED) {
        this.idKoszyk_SELECTED = idKoszyk_SELECTED;
    }

    public String getNazwa_kodu_rabatowego() {
        return nazwa_kodu_rabatowego;
    }

    public void setNazwa_kodu_rabatowego(String nazwa_kodu_rabatowego) {
        this.nazwa_kodu_rabatowego = nazwa_kodu_rabatowego;
    }

    public String getIdKoszyk_SELECTED_Rabat() {
        return idKoszyk_SELECTED_Rabat;
    }

    public void setIdKoszyk_SELECTED_Rabat(String idKoszyk_SELECTED_Rabat) {
        this.idKoszyk_SELECTED_Rabat = idKoszyk_SELECTED_Rabat;
    }

    public String getIdkoszyk_produkty() {
        return idkoszyk_produkty;
    }

    public void setIdkoszyk_produkty(String idkoszyk_produkty) {
        this.idkoszyk_produkty = idkoszyk_produkty;
    }

    public String getIdKoszyk_SELECTED_Usun() {
        return idKoszyk_SELECTED_Usun;
    }

    public void setIdKoszyk_SELECTED_Usun(String idKoszyk_SELECTED_Usun) {
        this.idKoszyk_SELECTED_Usun = idKoszyk_SELECTED_Usun;
    }

    public Double getRabatt() {
        return rabatt;
    }

    public void setRabatt(Double rabatt) {
        this.rabatt = rabatt;
    }

    public Integer getCena_przed() {
        return cena_przed;
    }

    public void setCena_przed(Integer cena_przed) {
        this.cena_przed = cena_przed;
    }

    public Integer getCena_po() {
        return cena_po;
    }

    public void setCena_po(Integer cena_po) {
        this.cena_po = cena_po;
    }

    public String getIdKoszyk_SELECTED_Edytuj() {
        return idKoszyk_SELECTED_Edytuj;
    }

    public void setIdKoszyk_SELECTED_Edytuj(String idKoszyk_SELECTED_Edytuj) {
        this.idKoszyk_SELECTED_Edytuj = idKoszyk_SELECTED_Edytuj;
    }

    public String getIdKoszyk_SELECTED_Realizuj() {
        return idKoszyk_SELECTED_Realizuj;
    }

    public void setIdKoszyk_SELECTED_Realizuj(String idKoszyk_SELECTED_Realizuj) {
        this.idKoszyk_SELECTED_Realizuj = idKoszyk_SELECTED_Realizuj;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNr_domu() {
        return nr_domu;
    }

    public void setNr_domu(String nr_domu) {
        this.nr_domu = nr_domu;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

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
