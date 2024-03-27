package ita.vs;
import io.smallrye.mutiny.Uni;
import ita.vs.models.Vinograd;
import ita.vs.models.VinogradPost;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import ita.vs.repo.vinogradRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/vinograd")
public class VinogradREST {
    private static final Logger log = Logger.getLogger(VinogradREST.class.getName());

    @Channel("vinograds") Emitter<String> Emitter;

    @Inject
    vinogradRepository VinogradRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    //@Transactional
    public Uni<Vinograd> addVinograd(VinogradPost v) {

        Vinograd vinograd = new Vinograd();
        vinograd.naziv = v.naziv;
        vinograd.povrsina = v.povrsina;
        vinograd.letozacetka = v.letozacetka;
        Logger.getLogger(VinogradREST.class.getName()).info("Adding vinograd: " + vinograd.naziv);
        Emitter.send("addVinograd: " + vinograd.naziv);

        return VinogradRepository.addVinograd(vinograd);

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Vinograd>> getVinograds() {
        log.info("Getting vinograds");
        Emitter.send("getVinograd");
        //return VinogradRepository.listAll().map(vinograds -> vinograds.stream().collect(Collectors.toList()));
        return VinogradRepository.listAll()
                .onItem()
                .transform(vinograds -> vinograds.stream().map(vinograd -> {
                    Vinograd v = new Vinograd();
                    v.setId(vinograd.getId());
                    v.naziv = vinograd.naziv;
                    v.povrsina = vinograd.povrsina;
                    v.letozacetka = vinograd.letozacetka;
                    return v;
                }).collect(Collectors.toList()));
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    //@Transactional
    public Uni<Boolean> deleteVinograd(@PathParam("id") Long id) {
        log.info("Deleting vinograd: " + id);
        Emitter.send("deleteVinograd: " + id);
        //return VinogradRepository.findById(id).chain(vinograd -> VinogradRepository.delete(vinograd).map(vinograd1 -> vinograd));
        return VinogradRepository.deleteVinograd(id);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Vinograd> getVinogradById(@PathParam("id") Long id) {
        log.info("Getting vinograd: " + id);
        Emitter.send("getVinogradById: " + id);
        return VinogradRepository.findById(id);
    }
}
