package warstwa_Model.Strona_Admin;

import DataBase_AutoGenerated.Osoba;
import DataBase_AutoGenerated.Powiadomienia;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Klasa od strony admina
 * sluzaca do obslugi powiadomien
 */
@Stateless
@LocalBean
public class PowiadomieniaDAO {


    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Metoda tworzaca SELECT pobierajacy dane o powiadomieniach
     * @param i id powiadomienia do pobrania
     * @return zwraca liste powiadomien
     */
    public List<Powiadomienia> getListOfNotifications(int i) {
        return entityManager.createQuery("SELECT p FROM Powiadomienia p WHERE p.idPowiadomienia =" + i, Powiadomienia.class).getResultList();
    }

    /**
     * Metoda tworzaca SELECT pobierajacy id powiadomien
     * @return metoda zwraca liste ze wszystkimi id
     */
    public List<Integer> getIdOfNotifications(){
        Query query = entityManager.createNativeQuery("SELECT idPowiadomienia FROM powiadomienia");
        List list =  query.getResultList();

        return list;
    }

    /**
     * Metoda tworzaca SELECT pobierajacy id osób
     * @param i id osoby
     * @return metoda zwraca liste
     */
    public List<Osoba> getListOfPersons(int i) {
        return entityManager.createQuery("SELECT o FROM Osoba o WHERE o.idOsoba =" + i, Osoba.class).getResultList();
    }

    /**
     * Lista pobierajaca idOsób
     * @return zwraca liste ze wszystkimi id
     */
    public String getIdOfPersons(int i){
        Query query = entityManager.createNativeQuery("SELECT idOsoba_FK FROM Powiadomienia WHERE idPowiadomienia = " + i);
        List list =  query.getResultList();

        return String.valueOf(list).replace("[","").replace("]","");
    }

    /**
     * Usuwa powiadomienia
     * @param i
     */
    public void deleteNotification(int i){
        Powiadomienia pow = entityManager.find(Powiadomienia.class, i);
        entityManager.remove(pow);
    }

    /**
     * Tworzy nowe powiadomienie
     * @param id
     * @param temat
     * @param tresc
     * @param odKogo
     * @param doKogo
     */
    public void createNotification(int id, String temat, String tresc, int odKogo, int doKogo ){
        entityManager.createNativeQuery("INSERT INTO powiadomienia VALUES(" + id + "," + "'" + temat + "'" + "," + "'" + tresc + "'" + "," + odKogo + "," + doKogo + ")").executeUpdate();
    }

}
