package warstwa_Controller.general;

import warstwa_Model.general.RegisterDAO;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.security.MessageDigest;

@ManagedBean
@RequestScoped
public class Register{

    private String username;
    private String password;
    private String email;
    private String imie;
    private String nazwisko;


    @EJB
    private RegisterDAO registerDAO;


    /**
     * Metoda dodającego nowego użytkownika na stronie z uprawnieniami klienta
     */
    /** Baza */ public void register(){
        //dodaj tylko osobe
        registerDAO.dodaj__OSOBE(registerDAO.getSizeOfOsoby()+1,this.username, this.password = getSHA256(this.password), this.email, this.imie, this.nazwisko);

        // dodaj tylko user_groups
        registerDAO.dodaj__USER_GROUPS(registerDAO.getSizeOfOsoby());

        // dodaj tylko klienta
        registerDAO.dodaj__KLIENTA(registerDAO.getSizeOfKlienci()+1, registerDAO.getSizeOfOsoby());

        // dodaj koszyk po utworzeniu
        registerDAO.dodaj__KOSZYK_ZAMOWIENIE(registerDAO.getSizeOfKoszyk_Zamowienia()+1, registerDAO.getSizeOfKlienci());


        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/index.xhtml");
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

    /**
     * Gettery i Settery
     * @return
     */
    /*************************/
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNazwisko() { return nazwisko; }

    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    public String getImie() { return imie; }

    public void setImie(String imie) { this.imie = imie; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
    /*************************/
}
