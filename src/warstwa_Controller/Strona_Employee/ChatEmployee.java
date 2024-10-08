package warstwa_Controller.Strona_Employee;

import DataBase_AutoGenerated.Chat;
import warstwa_Model.Strona_Employee.ChatEmployeeDAO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;

@ManagedBean
@RequestScoped
public class ChatEmployee {

    private List<Chat> chatList;
    private String text_employee;


    @EJB
    ChatEmployeeDAO chatEmployeeDAO;


    /**
     * Metoda inicjująca
     */
    @PostConstruct
    public void init(){
        chatList = chatEmployeeDAO.loadAllChat();
    }

    /**
     * Metoda wczytująca chat
     */
    public void loadChat(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            context.getExternalContext().redirect("/_Employee/ChatEmployee.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** Test */ public String loadChat___TEST(){
        return "/_Employee/ChatEmployee.xhtml";
    }

    /**
     * Metoda odpowiedzialna za dodawanie nowych wiadomości
     */
    public void addText(){
        chatEmployeeDAO.dodajTextUser(getText_employee());
        init();
    }

    /**
     * Gettery i Settery
     * @return
     */
    /*************************/
    public String getText_employee() {
        return text_employee;
    }

    public void setText_employee(String text_employee) {
        this.text_employee = text_employee;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }
    /*************************/
}
