package warstwa_Controller.Strona_User;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class Oferta_Test {

    @Test
    public void ___nextI___TEST(){
        Oferta oferta = new Oferta();
        oferta.setId(5);

        String result1 = oferta.nextI___TEST(1,5,null,null);
        assertEquals("zabezpieczenie nr 1 zadzialalo i nastapilo zapetlenie", result1);

        String result2 = oferta.nextI___TEST(2,0,"inna_kategoria","karty_graficzne");
        assertEquals("zabezpieczenie nr 2 zadzialalo i nastapilo zapetlenie", result2);

        String result3 = oferta.nextI___TEST(3,0,null,null);
        assertEquals("kolejny produkt zostal wyswietlony bez koniecznosci zapetlania", result3);
    }

    @Test
    public void ___search___TEST(){
        Oferta oferta = new Oferta();
        oferta.setWyszukaj("procesor");

        boolean result1 = oferta.search___TEST();
        assertTrue(result1);
    }

    @Test
    public void ___redirectToSomewhere___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.redirectToSomewhere___TEST("Antywirusy", "oprogramowanie");
        assertEquals("/faces/ofertaSklepu/oprogramowanie/Antywirusy.xhtml" , result1);
    }

    @Test
    public void ___procesory___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("Procesory", "Procesory");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/Procesory.xhtml", result1);
    }

    @Test
    public void ___karty_graficzne___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("KartyGraficzne", "KartyGraficzne");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/KartyGraficzne.xhtml", result1);
    }

    @Test
    public void ___plyty_glowne___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("PlytyGlowne", "PlytyGlowne");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/PlytyGlowne.xhtml", result1);
    }

    @Test
    public void ___zasilacze___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("Zasilacze", "Zasilacze");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/Zasilacze.xhtml", result1);
    }

    @Test
    public void ___pamieci_ram___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("PamieciRAM", "PamieciRAM");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/PamieciRAM.xhtml", result1);
    }

    @Test
    public void ___obudowy_komputerowe___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("ObudowyKomputerowe", "ObudowyKomputerowe");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/ObudowyKomputerowe.xhtml", result1);
    }

    @Test
    public void ___chlodzenia_procesora___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("ChlodzeniaProcesora", "ChlodzeniaProcesora");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/ChlodzeniaProcesora.xhtml", result1);
    }

    @Test
    public void ___dyski_twarde___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("DyskiTwarde", "DyskiTwarde");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/DyskiTwarde.xhtml", result1);
    }

    @Test
    public void ___laptopy___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("Laptopy", "Laptopy");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/Laptopy.xhtml", result1);
    }

    @Test
    public void ___antywirusy___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("Antywirusy", "Antywirusy");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/Antywirusy.xhtml", result1);
    }

    @Test
    public void ___programy_biurowe___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("ProgramyBiurowe", "ProgramyBiurowe");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/ProgramyBiurowe.xhtml", result1);
    }

    @Test
    public void ___systemy_operacyjne___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("SystemyOperacyjne", "SystemyOperacyjne");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/SystemyOperacyjne.xhtml", result1);
    }

    @Test
    public void ___drukarki___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("Drukarki", "Drukarki");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/Drukarki.xhtml", result1);
    }

    @Test
    public void ___klawiatury___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("Klawiatury", "Klawiatury");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/Klawiatury.xhtml", result1);
    }

    @Test
    public void ___myszki___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("Myszki", "Myszki");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/Myszki.xhtml", result1);
    }

    @Test
    public void ___monitory___TEST(){
        Oferta oferta = new Oferta();

        String result1 = oferta.procesory___TEST("Monitory", "Monitory");
        assertEquals("/faces/ofertaSklepu/podzespolyKomputerowe/Monitory.xhtml", result1);
    }

}