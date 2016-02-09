package sk.upjs.ics.autobazar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InzeratNakladne {

    private Long idP;

    private Long id;

    private String znacka;

    private String model;

    private String rocnik;

    private Long km;

    private Long objem;

    public String prevodovka;

    public Long vykon;

    private Date datumPridania;

    private Long cena;

    private byte[] obrazok;

    private boolean klimatizacia;

    private boolean tazneZariadenie;

    private boolean vyhrievaneSedadla;


    public byte[] getObrazok() {
        return obrazok;
    }

    public void setObrazok(byte[] obrazok) {
        this.obrazok = obrazok;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public boolean isKlimatizacia() {
        return klimatizacia;
    }

    public void setKlimatizacia(boolean klimatizacia) {
        this.klimatizacia = klimatizacia;
    }

    public boolean isTazneZariadenie() {
        return tazneZariadenie;
    }

    public void setTazneZariadenie(boolean tazneZariadenie) {
        this.tazneZariadenie = tazneZariadenie;
    }

    public boolean isVyhrievaneSedadla() {
        return vyhrievaneSedadla;
    }

    public void setVyhrievaneSedadla(boolean vyhrievaneSedadla) {
        this.vyhrievaneSedadla = vyhrievaneSedadla;
    }

    public Long getIdP() {
        return idP;
    }

    public void setIdP(Long idP) {
        this.idP = idP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZnacka() {
        return znacka;
    }

    public void setZnacka(String znacka) {
        this.znacka = znacka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRocnik() {
        return rocnik;
    }

    public void setRocnik(String rocnik) {
        this.rocnik = rocnik;
    }

    public Long getKm() {
        return km;
    }

    public void setKm(Long km) {
        this.km = km;
    }

    public Long getObjem() {
        return objem;
    }

    public void setObjem(Long objem) {
        this.objem = objem;
    }

    public String getPrevodovka() {
        return prevodovka;
    }

    public void setPrevodovka(String prevodovka) {
        this.prevodovka = prevodovka;
    }

    public Long getVykon() {
        return vykon;
    }

    public void setVykon(Long vykon) {
        this.vykon = vykon;
    }

    public Date getDatumPridania() {
        return datumPridania;
    }

    public void setDatumPridania(Date datumPridania) {
        this.datumPridania = datumPridania;
    }

    public Long getCena() {
        return cena;
    }

    public void setCena(Long cena) {
        this.cena = cena;
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("d.M.yyyy");

    @Override
    public String toString() {
        return this.znacka + " " + this.model + ", Rok vyroby: " + this.rocnik + ", Objem: " + this.objem + "\n\n";
    }

    public String toString2() {
        String klimatizacia;
        String tazneZariadenie;
        String vyhrievaneSedadla;

        if (this.isKlimatizacia()) {
            klimatizacia = "ano";
        } else {
            klimatizacia = "nie";
        }
        if (this.isTazneZariadenie()) {
            tazneZariadenie = "ano";
        } else {
            tazneZariadenie = "nie";
        }
        if (this.isVyhrievaneSedadla()) {
            vyhrievaneSedadla = "ano";
        } else {
            vyhrievaneSedadla = "nie";
        }
        return "Znacka: " + this.znacka + "\n"
                + "Model: " + this.model + "\n"
                + "Rok vyroby: " + this.rocnik + "\n"
                + "Objem: " + this.objem + "\n"
                + "Vykon: " + this.vykon + " KW\n"
                + "Pocet najazdenych kilometrov: " + this.km + "\n"
                + "Prevodovka: " + this.getPrevodovka() + "\n"
                + "Klimatizacia: " + klimatizacia + "\n"
                + "Tazne zariadenie: " + tazneZariadenie + "\n"
                + "Vyhrievane sedadla: " + vyhrievaneSedadla + "\n"
                + "Datum pridania: " + dateFormat.format(this.datumPridania);
    }
}
