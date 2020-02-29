package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto warasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        warasto = new Varasto(5, 4); // tilavuus 5, alkusaldo 4, tilaa 1
    }

    //konstruktorien testit
    // Varasto(double tilavuus)
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriNollaanJosArvoLiianPieni() {
        Varasto v = new Varasto(-8);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    // toinen konstruktori Varasto(double tilavuus, double alkuSaldo)
    @Test
    public void asettaaOikeanTilavuuden() {
        assertEquals(5, warasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void eiSalliNegatiivistaSaldoa() {
        warasto = new Varasto(5, -6);
        assertEquals(0, warasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void asettaaOikeanSaldon() {
        assertEquals(4, warasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiSalliNegatiivistaTilavuutta() {
        warasto = new Varasto(-5, 6);
        assertEquals(0, warasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void eiAnnaYlitayttaa() {
        warasto = new Varasto(5, 6);
        assertEquals(5, warasto.getSaldo(), vertailuTarkkuus);
    }

    // lisäysmetodin testit
    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivistaEiVoiLisata() {
        warasto.lisaaVarastoon(-3);
        assertEquals(4, warasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liiallinenLisays() {
        warasto.lisaaVarastoon(10);
        assertEquals(5, warasto.getSaldo(), vertailuTarkkuus);
    }

    //vapaan tilan testit
    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    //poistometodin testit
    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOtto() {
        warasto.otaVarastosta(-3);
        assertEquals(4, warasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianSuuriOtto() {
        warasto.otaVarastosta(7);
        assertEquals(7, warasto.getSaldo(), vertailuTarkkuus);
    }

    //toStringin testit
    @Test
    public void oikeaTeksti() {
        String t = warasto.toString();
        String oikea = "saldo = " + warasto.getSaldo() + ", vielä tilaa " + warasto.paljonkoMahtuu();
        assertEquals(oikea, t);
    }
}
