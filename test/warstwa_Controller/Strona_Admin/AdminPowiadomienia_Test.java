package warstwa_Controller.Strona_Admin;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AdminPowiadomienia_Test {

    @Test
    public void loadFiles___TEST(){
        AdminPowiadomienia adminPowiadomienia = new AdminPowiadomienia();

        String result1 = adminPowiadomienia.loadFiles___TEST();
        assertEquals("/_Admin/Notifications.xhtml",result1);
    }

    @Test
    public void NewNotification___TEST(){
        AdminPowiadomienia adminPowiadomienia = new AdminPowiadomienia();

        String result1 = adminPowiadomienia.NewNotification___TEST();
        assertEquals("/_Admin/NewNotification.xhtml",result1);
    }
}
