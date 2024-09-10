package warstwa_Controller.Strona_Admin;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AdminFaktury_Test {

    @Test
    public void EditClient___TEST(){
        AdminFaktury adminFaktury = new AdminFaktury();

        String result1 = adminFaktury.openFaktury___TEST();
        assertEquals("/_Admin/FakturyAdmin.xhtml",result1);
    }

}
