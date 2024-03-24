package ita.vs;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.vertx.VertxContextSupport;
import ita.vs.models.Vinograd;
import ita.vs.repo.vinogradRepository;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import org.hibernate.reactive.mutiny.Mutiny;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
class VinogradTest {

    @Test
    void TestDodajVinograd() {

        Vinograd vinograd = new Vinograd();
        vinograd.naziv = "Vinograd1";
        vinograd.povrsina = 100;
        vinograd.letozacetka = "2021";

        given()
                .contentType("application/json")
                .body(vinograd)
                .when().post("/vinograd")
                .then()
                .statusCode(200)
                .body("naziv", is("Vinograd1"));
    }

    @Test
    void TestGetVinograds() {
        given()
                .when().get("/vinograd")
                .then()
                .statusCode(200)
                .body("$.size()", is(1));
    }

    @Test
    void TestDeleteVinograd() {
        given()
                .when().delete("/vinograd/1")
                .then()
                .statusCode(200);
    }





}