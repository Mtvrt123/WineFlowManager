package ita.ws.winestorage.dao;

import org.springframework.data.repository.CrudRepository;

import ita.ws.winestorage.vao.Vino;

public interface VinoRepository extends CrudRepository<Vino, Integer> 
{
    //find by name
    public Vino findByNaziv(String naziv);
}
