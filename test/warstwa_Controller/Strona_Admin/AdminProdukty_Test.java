package warstwa_Controller.Strona_Admin;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AdminProdukty_Test {

    @Test
    public void loadFiles___TEST(){
        AdminProdukty adminProdukty = new AdminProdukty();

        String result1 = adminProdukty.loadFiles___TEST();
        assertEquals("/_Admin/Products.xhtml",result1);
    }

}
