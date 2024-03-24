package ita.uporabniki;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.RunOnVirtualThread;
import ita.uporabniki.repo.UporabnikRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.logging.Logger;

@GrpcService
public class UporabnikiGrpcService extends UporabnikiServiceGrpc.UporabnikiServiceImplBase
{
    private static final Logger log = Logger.getLogger(UporabnikiServiceGrpc.class.toString());

    @Inject
    UporabnikRepository uporabnikRepository;


    @RunOnVirtualThread
    @Override
    @Transactional
    public void dodajUporabnika(UporabnikRequest request, StreamObserver<UporabnikReply> responseObserver) {
        log.info("createUser");
        ita.uporabniki.models.Uporabnik uporabnik = new ita.uporabniki.models.Uporabnik();
        uporabnik.ime = request.getIme();
        uporabnik.priimek = request.getPriimek();
        uporabnik.email = request.getEmail();

        uporabnikRepository.persist(uporabnik);

        responseObserver.onNext(UporabnikReply.newBuilder()
                .setId(String.valueOf(uporabnik.id))
                .setIme(uporabnik.ime)
                .setPriimek(uporabnik.priimek)
                .setEmail(uporabnik.email)
                .build());
        responseObserver.onCompleted();
    }

    @RunOnVirtualThread
    @Override
    @Transactional
    public void vrniUporabnika(UporabnikRequestID request, StreamObserver<UporabnikReply> responseObserver) {
        log.info("getUser");
        ita.uporabniki.models.Uporabnik uporabnik = uporabnikRepository.findById(Long.parseLong(request.getId()));

        responseObserver.onNext(UporabnikReply.newBuilder()
                .setIme(uporabnik.ime)
                .setPriimek(uporabnik.priimek)
                .setEmail(uporabnik.email)
                .build());
        responseObserver.onCompleted();
    }

    @RunOnVirtualThread
    @Override
    @Transactional
    public void posodobiUporabnika(UporabnikRequestUpdate request, StreamObserver<UporabnikReply> responseObserver) {
        log.info("updateUser");
        ita.uporabniki.models.Uporabnik uporabnik = uporabnikRepository.findById(Long.parseLong(request.getId()));
        uporabnik.ime = request.getIme();
        uporabnik.priimek = request.getPriimek();
        uporabnik.email = request.getEmail();
        uporabnikRepository.persist(uporabnik);

        responseObserver.onNext(UporabnikReply.newBuilder()
                .setIme(uporabnik.ime)
                .setPriimek(uporabnik.priimek)
                .setEmail(uporabnik.email)
                .build());
        responseObserver.onCompleted();
    }

    @RunOnVirtualThread
    @Override
    @Transactional
    public void izbrisiUporabnika(UporabnikRequestID request, StreamObserver<UporabnikReply> responseObserver) {
        log.info("deleteUser");
        ita.uporabniki.models.Uporabnik uporabnik = uporabnikRepository.findById(Long.parseLong(request.getId()));
        uporabnikRepository.delete(uporabnik);

        responseObserver.onNext(UporabnikReply.newBuilder()
                .setIme(uporabnik.ime)
                .setPriimek(uporabnik.priimek)
                .setEmail(uporabnik.email)
                .build());
        responseObserver.onCompleted();
    }

    @RunOnVirtualThread
    @Override
    @Transactional
    public void healthCheck(HealthCheckRequest request, StreamObserver<HealthCheckReply> responseObserver) {
        log.info("healthCheck");
        responseObserver.onNext(HealthCheckReply.newBuilder()
                .setStatus("OK")
                .build());
        responseObserver.onCompleted();
    }

}
