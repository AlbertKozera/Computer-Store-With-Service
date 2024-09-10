package warstwa_Controller.Strona_User;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

public class KoszykZakupowy_Test {

    @Test
    public void getIdKoszykFromSelectedRowToEdit___TEST(){
        KoszykZakupowy koszykZakupowy = new KoszykZakupowy();
        HashMap<String, String> test_HashMap = new HashMap<>();
        test_HashMap.put("idKoszyk", "1");

        koszykZakupowy.getIdKoszykFromSelectedRowToEdit(test_HashMap);
        String result1 = koszykZakupowy.getIdKoszyk_SELECTED_Edytuj();
        assertEquals("1" , result1);
    }

    @Test
    public void getCurrentIdKoszykDeleteFromSelectedRow___TEST(){
        KoszykZakupowy koszykZakupowy = new KoszykZakupowy();
        HashMap<String, String> test_HashMap = new HashMap<>();
        test_HashMap.put("idKoszyk", "1");

        assertEquals( koszykZakupowy.getCurrentIdKoszykDeleteFromSelectedRow___TEST(test_HashMap).isEmpty(), true);
    }

    @Test
    public void getCurrentIdKoszykFKFromSelectedRow___TEST(){
        KoszykZakupowy koszykZakupowy = new KoszykZakupowy();
        HashMap<String, String> test_HashMap = new HashMap<>();
        test_HashMap.put("idkoszyk_produkty", "1");

        assertEquals( koszykZakupowy.getCurrentIdKoszykFKFromSelectedRow___TEST(test_HashMap).isEmpty(), true);
    }

    @Test
    public void refreshFinalPriceOnScreen___TEST(){
        KoszykZakupowy koszykZakupowy = new KoszykZakupowy();

        String result1 = koszykZakupowy.refreshFinalPriceOnScreen___TEST(999);
        assertEquals("999",result1);

        String result2 = koszykZakupowy.refreshFinalPriceOnScreen___TEST(0);
        assertEquals("0.00",result2);
    }

    @Test
    public void takeCurrentObjectMap___TEST(){
        KoszykZakupowy koszykZakupowy = new KoszykZakupowy();
        HashMap<String, String> test_HashMap = new HashMap<>();
        test_HashMap.put("idKoszyk", "1");

        String result1 = koszykZakupowy.takeCurrentObjectMap___TEST(test_HashMap);
        assertEquals("1",result1);
    }


}
