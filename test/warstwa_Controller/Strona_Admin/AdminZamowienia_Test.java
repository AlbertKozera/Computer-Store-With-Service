package warstwa_Controller.Strona_Admin;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AdminZamowienia_Test {

    @Test
    public void loadFiles___TEST(){
        AdminZamowienia adminZamowienia = new AdminZamowienia();

        String result1 = adminZamowienia.loadFiles___TEST();
        assertEquals("/_Admin/Order.xhtml",result1);
    }
}
