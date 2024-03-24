package ita.ws.winestorage.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Document("vina")
@Entity
@Table(name = "Vino")
public class Vino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String naziv;
    private String sorta;
    private String proizvajalec;
    private String drzava;
    private String regija;
    private String opis;

    public Vino() {
    }

    public Vino(int id, String naziv, String sorta, String proizvajalec, String drzava, String regija, String opis) {
        this.id = id;
        this.naziv = naziv;
        this.sorta = sorta;
        this.proizvajalec = proizvajalec;
        this.drzava = drzava;
        this.regija = regija;
        this.opis = opis;
    }

    public Vino(Vino vino) {
        this.id = vino.id();
        this.naziv = vino.naziv();
        this.sorta = vino.sorta();
        this.proizvajalec = vino.proizvajalec();
        this.drzava = vino.drzava();
        this.regija = vino.regija();
        this.opis = vino.opis();
    }

    public Vino(ita.ws.winestorage.dto.Vino vino){
        this.id = vino.id();
        this.naziv = vino.naziv();
        this.sorta = vino.sorta();
        this.proizvajalec = vino.proizvajalec();
        this.drzava = vino.drzava();
        this.regija = vino.regija();
        this.opis = vino.opis();
    }

    public Vino(ita.ws.winestorage.dto.post.VinoPost vino) {
        this.naziv = vino.naziv();
        this.sorta = vino.sorta();
        this.proizvajalec = vino.proizvajalec();
        this.drzava = vino.drzava();
        this.regija = vino.regija();
        this.opis = vino.opis();
    }

    public void updateFrom(ita.ws.winestorage.dto.Vino vino) {
        setId(id);
        setNaziv(naziv);
        setSorta(sorta);
        setproizvajalec(proizvajalec);
        setDrzava(drzava);
        setRegija(regija);
        setOpis(opis);
    }

    public ita.ws.winestorage.dto.Vino toDto() {
        return new ita.ws.winestorage.dto.Vino(id, naziv, sorta, proizvajalec, drzava, regija, opis);
    }

    public int id() {
        return id;
    }

    public String naziv() {
        return naziv;
    }

    public String sorta() {
        return sorta;
    }

    public String proizvajalec() {
        return proizvajalec;
    }

    public String drzava() {
        return drzava;
    }

    public String regija() {
        return regija;
    }

    public String opis() {
        return opis;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setSorta(String sorta) {
        this.sorta = sorta;
    }

    public void setproizvajalec(String proizvajalec) {
        this.proizvajalec = proizvajalec;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public void setRegija(String regija) {
        this.regija = regija;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
    @Override
    public String toString() {
        return "Vino{" + "id=" + id + ", naziv=" + naziv + ", sorta=" + sorta + ", proizvajalec=" + proizvajalec + ", drzava=" + drzava + ", regija=" + regija + ", opis=" + opis + '}';
    }

    public void update(ita.ws.winestorage.dto.Vino vino) {
        this.naziv = vino.naziv();
        this.sorta = vino.sorta();
        this.proizvajalec = vino.proizvajalec();
        this.drzava = vino.drzava();
        this.regija = vino.regija();
        this.opis = vino.opis();
    }

}
