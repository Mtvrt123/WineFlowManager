package ita.uporabniki.repo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import ita.uporabniki.models.Uporabnik;

@ApplicationScoped
public class UporabnikRepository implements PanacheRepository<Uporabnik> {

}
