package warstwa_Controller.Strona_Employee;


import org.junit.Test;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

@ManagedBean
@RequestScoped
public class KodRabatowy_Test {

    @Test
    public void load_dodajKodyRabatowe___TEST(){
        KodRabatowy kodRabatowy = new KodRabatowy();

        String result1 = kodRabatowy.load_dodajKodyRabatowe___TEST();
        assertEquals("/_Employee/dodajKodRabatowy.xhtml",result1);
    }

    @Test
    public void loadKodyRabatowe___TEST(){
        KodRabatowy kodRabatowy = new KodRabatowy();

        String result1 = kodRabatowy.loadKodyRabatowe___TEST();
        assertEquals("/_Employee/KodRabatowy.xhtml",result1);
    }

    @Test
    public void usunKodRabatowy___TEST(){
        KodRabatowy kodRabatowy = new KodRabatowy();
        HashMap<String, String> test_HashMap = new HashMap<>();
        test_HashMap.put("idkody_rabatowe", "1");

        assertEquals( kodRabatowy.usunKodRabatowy___TEST(test_HashMap).isEmpty(), true);
    }

}
