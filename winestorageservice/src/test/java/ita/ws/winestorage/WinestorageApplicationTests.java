package ita.ws.winestorage;


import java.util.stream.StreamSupport;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;

import ita.ws.winestorage.dao.VinoRepository;
import ita.ws.winestorage.vao.Vino;

@SpringBootTest
@ActiveProfiles("dev")
class WinestorageApplicationTests {

	@Autowired
    private VinoRepository dao;

    Vino vino;

    @BeforeEach
    void beforeEach() {
		dao.deleteAll();

        dao.save(new Vino(1, "Vino", "Sorta", "Proizvajalec", "Drzava", "Regija", "Opis"));

		vino = dao.findByNaziv("Vino");
		
    }

	//DAO TESTS
	@Test
	void testSave() {
		Vino vino = new Vino(2,"Vino2", "Sorta2", "Proizvajalec2", "Drzava2", "Regija2", "Opis2");
		dao.save(vino);
		assert(
			StreamSupport.stream(dao.findAll().spliterator(), false).count() == 2
		);
	}

	@Test
	void testFindById() {
		assert(
			dao.findById(vino.id()).get().naziv().equals("Vino")
		);
	}

	@Test
	void testFindAll() {
		assert(
			StreamSupport.stream(dao.findAll().spliterator(), false).count() == 1
		);
	}

	@Test
	void testDelete() {
		dao.delete(vino);
		assert(
			StreamSupport.stream(dao.findAll().spliterator(), false).count() == 0
		);
	}

	@Test
	void testDeleteAll() {
		dao.deleteAll();
		assert(
			StreamSupport.stream(dao.findAll().spliterator(), false).count() == 0
		);
	}

	@Test
	void testCount() {
		assert(
			dao.count() == 1
		);
	}
}


