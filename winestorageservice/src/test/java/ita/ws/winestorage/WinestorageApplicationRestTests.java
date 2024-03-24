package ita.ws.winestorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import ita.ws.winestorage.dao.VinoRepository;
import ita.ws.winestorage.dto.post.VinoPost;
import ita.ws.winestorage.rest.VinoController;
import ita.ws.winestorage.vao.Vino;

@SpringBootTest
@ActiveProfiles("dev")
class WinestorageApplicationRestTests {

	@Autowired
    private VinoRepository dao;

	@Autowired
	private VinoController controller;

    int id = 1;

    @BeforeEach
    void beforeEach() {

		dao.deleteAll();

        VinoPost vino = new VinoPost("Vino", "Sorta", "Proizvajalec", "Drzava", "Regija", "Opis");

		var v = controller.create(vino);
		
		id = v.getBody().id();

    }

	//REST TESTS
	@Test
	void testGetAll() {
		assert(
			StreamSupport.stream(controller.getAll().spliterator(), false).count() == 1
		);
	}

	@Test
	void testGetById() {
		ResponseEntity<ita.ws.winestorage.dto.Vino> response = controller.getById(1);
		if (response != null) {

			System.err.println(response.getBody());

			//assertEquals(response.getStatusCode(), HttpStatus.OK);
			//assertTrue(response.getBody());

		} else {
			assertTrue(false);
}
	}

	@Test
	void testPost() {
		VinoPost vino = new VinoPost("Vino2", "Sorta2", "Proizvajalec2", "Drzava2", "Regija2", "Opis2");
		controller.create(vino);
		assert(
			StreamSupport.stream(controller.getAll().spliterator(), false).count() == 2
		);
	}

	@Test
	void testPut() {
		ita.ws.winestorage.dto.Vino vino = new ita.ws.winestorage.dto.Vino(id, "Vino2", "Sorta2", "Proizvajalec2", "Drzava2", "Regija2", "Opis2");
		var res = controller.update(id, vino);

		assertEquals(
			"Vino2", res.getBody().naziv()
		);
	}

	@Test
	void testRestDelete() {
		controller.delete(id);
		assert(
			StreamSupport.stream(controller.getAll().spliterator(), false).count() == 0
		);
	}

	
}


