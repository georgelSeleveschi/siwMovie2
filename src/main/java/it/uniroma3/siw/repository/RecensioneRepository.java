package it.uniroma3.siw.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;

public interface RecensioneRepository extends CrudRepository<Recensione, Long> {
	public boolean existsByUtente_IdAndFilm_Id(Long id,Long film);
	
	@Query("SELECT r FROM Recensione r WHERE r.utente=?1")
	List<Recensione> findAllByUtente(User u);
	
}
