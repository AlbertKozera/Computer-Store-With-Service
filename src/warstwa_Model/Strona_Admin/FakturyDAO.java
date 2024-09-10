package warstwa_Model.Strona_Admin;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class FakturyDAO {


    @PersistenceContext
    private EntityManager entityManager;


    public List<Object[]> getFakturyForKlient() {
        return entityManager.createNativeQuery("SELECT f.idKlient_FK, f.Nazwa, f.Cena, f.Ilosc_produktow, f.Data_sprzedazy, o.Imie, o.Nazwisko, o.Miasto, o.Ulica, o.NrDomu, o.KodPocztowy \n" +
                "FROM faktura f, osoba o, klient k \n" +
                "WHERE f.idKlient_FK = k.idKlient \n" +
                "AND o.idOsoba = k.Osoba_idOsoba").getResultList();
    }

}
