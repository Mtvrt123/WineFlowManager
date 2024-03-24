package ita.ws.winestorage.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import ita.ws.winestorage.dao.VinoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ita.ws.winestorage.vao.Vino;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin
@RestController
public class VinoController {
    
    private static final Logger log = Logger.getLogger(VinoController.class.toString());
    
    private VinoRepository dao;
    
    private static final String BASE_URL = "/vino";
    private static final String BASE_URL_VINA = "/vina";
    private static final String BASE_URL_ID = "/vino/{id}";
    
    public VinoController(VinoRepository dao) {
        this.dao = dao;
    }
    
    static List<ita.ws.winestorage.dto.Vino> translateProductListToDtoList(Iterable<Vino> list) {
		List<ita.ws.winestorage.dto.Vino> ret=new ArrayList<>();
		for (Vino sc :list)
			ret.add(sc.toDto());
		return ret;
	}

    @GetMapping(BASE_URL_VINA)
    public Iterable<ita.ws.winestorage.dto.Vino> getAll() {
        log.info("GET /api/vina");

        log.info(dao.findAll().toString());
        
        return translateProductListToDtoList(dao.findAll());
    }
  
    @GetMapping(BASE_URL_ID)
    public ResponseEntity<ita.ws.winestorage.dto.Vino> getById(@RequestParam int id) {
        log.info("GET /api/vino/" + id);
        
        Optional<Vino> vino = dao.findById(id);
        if (vino.isPresent()) {
            return ResponseEntity.ok(vino.get().toDto());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(BASE_URL)
    public ResponseEntity<ita.ws.winestorage.dto.Vino> create(@RequestBody ita.ws.winestorage.dto.post.VinoPost vino) {
        log.info("POST /api/vino");

        log.info(vino.toString());

        Vino vao = new Vino(vino);

        dao.save(vao);

        return ResponseEntity.ok(vao.toDto());
    }

    @PutMapping(BASE_URL_ID)
    public ResponseEntity<ita.ws.winestorage.dto.Vino> update(@PathVariable int id, @RequestBody ita.ws.winestorage.dto.Vino vino) {
        log.info("PUT /api/vino/" + id);

        Optional<Vino> vinoToUpdate = dao.findById(id);
        if (vinoToUpdate.isPresent()) {
            Vino vao = vinoToUpdate.get();
            vao.update(vino);
            dao.save(vao);
            return ResponseEntity.ok(vao.toDto());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(BASE_URL_ID)
    public ResponseEntity<String> delete(@PathVariable int id) {
        log.info("DELETE /api/vino/" + id);
        
        Optional<Vino> vino = dao.findById(id);
        if (vino.isPresent()) {
            Vino vinoToDelete = vino.get(); 
            dao.delete(vinoToDelete);
            return ResponseEntity.ok("Vino je izbrisano.");
        }
        return ResponseEntity.notFound().build();
    }
}
