package warstwa_Controller.general;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@RequestScoped
public class Logout {

    /**
     * Metoda obsługująca wylogowanie
     * @return
     */
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        return "/index?faces-redirect=true";
    }

    /**
     * Get the login username if it exists
     * @return
     */
    public String getUsername() {
        String user = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        return user;
    }

    /**
     * Metoda sprawdzająca czy ktokolwiek jest zalogowany
     * @return
     */
    public boolean isAnyoneLoggedIn() {
        String user = this.getUsername();
        boolean result = !((user == null)|| user.isEmpty());
        return result;
    }

    /**
     * Metoda sprawdzająca czy klient jest zalogowany
     * @return
     */
    public boolean isUserLoggedIn(){
        boolean user = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USER");
        return user;
    }

    /**
     * Metoda sprawdzająca czy admin jest zalogowany
     * @return
     */
    public boolean isAdminLoggedIn(){
        boolean admin = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMIN");
        return admin;
    }

    /**
     * Metoda sprawdzająca czy pracownik jest zalogowany
     * @return
     */
    public boolean isEmployeeLoggedIn(){
        boolean employee = FacesContext.getCurrentInstance().getExternalContext().isUserInRole("EMPLOYEE");
        return employee;
    }

}
