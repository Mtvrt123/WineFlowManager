package ita.vs.vinogradservice.models;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


//PanacheEntityBase
//PanacheEntity
@Entity
public class Vinograd extends PanacheEntityBase
{
    @Id @GeneratedValue
    private Long id;
    public String naziv;
    public float povrsina;
    public String letozacetka;

    public Vinograd() {
    }

    public Vinograd(Long id, String naziv, float povrsina, String letozacetka) {
        this.id = id;
        this.naziv = naziv;
        this.povrsina = povrsina;
        this.letozacetka = letozacetka;
    }

    public Vinograd(String naziv, float povrsina, String letozacetka) {
        this.naziv = naziv;
        this.povrsina = povrsina;
        this.letozacetka = letozacetka;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public float getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(float povrsina) {
        this.povrsina = povrsina;
    }

    public String getLetozacetka() {
        return letozacetka;
    }

    public void setLetozacetka(String letozacetka) {
        this.letozacetka = letozacetka;
    }

}