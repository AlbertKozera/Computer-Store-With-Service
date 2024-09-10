package warstwa_Controller.Strona_Admin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;


@ManagedBean
@RequestScoped
/**
 * Klasa odpowiedzialna za kontrolowanie produktów
 * w zakładce admina
 */
public class AdminProdukty {

    /**
     * Metoda przekierowująca użytkownika na kolejną strone
     */
    public void loadFiles(){

        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Admin/Products.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Test */ public String loadFiles___TEST(){
        return "/_Admin/Products.xhtml";
    }
}
