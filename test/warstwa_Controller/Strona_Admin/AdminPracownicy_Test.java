package warstwa_Controller.Strona_Admin;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class AdminPracownicy_Test {

    @Test
    public void loadAddWorker___TEST(){
        AdminPracownicy adminPracownicy = new AdminPracownicy();



    }

    @Test
    public void loadFiles___TEST(){
        AdminPracownicy adminPracownicy = new AdminPracownicy();

        String result1 = adminPracownicy.loadFiles___TEST();
        assertEquals("/_Admin/Workers.xhtml",result1);
    }

    @Test
    public void getSHA256___TEST(){
        AdminPracownicy adminPracownicy = new AdminPracownicy();

        String result1 = adminPracownicy.getSHA256("1");
        assertEquals("6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b",result1);
    }
}












