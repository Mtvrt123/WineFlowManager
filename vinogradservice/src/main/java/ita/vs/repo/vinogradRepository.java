package ita.vs.repo;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import ita.vs.models.Vinograd;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class vinogradRepository  implements PanacheRepository<Vinograd> {

    public Uni<Vinograd> addVinograd(Vinograd vinograd) {
        return Panache.withTransaction(vinograd::persist)
                .replaceWith(vinograd);
    }

    public Uni<Vinograd> updateVinograd(Vinograd vinograd) {
        return Panache.withTransaction(vinograd::persist)
                .replaceWith(vinograd);
    }

    public Uni<Boolean> deleteVinograd(Long id) {
        return Panache.withTransaction(() -> deleteById(id));
    }

    public Uni<List<Vinograd>> listAll() {
        return findAll().list();
    }

}
