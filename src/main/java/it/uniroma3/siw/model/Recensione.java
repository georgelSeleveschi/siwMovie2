package it.uniroma3.siw.model;
import java.util.*;
import javax.persistence.*;
import javax.persistence.NamedQuery;



@Entity
public class Recensione {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	private String titolo;
	private int Valutazione;
	
	@Column(length=200)
	private String Testo;
	
	@ManyToOne
	private User utente;
	@ManyToOne
	private Movie film;
	
	
	public User getUtente() {
		return utente;
	}
	public void setUtente(User utente) {
		this.utente = utente;
	}
	public Movie getFilm() {
		return film;
	}
	public void setFilm(Movie film) {
		this.film = film;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public int getValutazione() {
		return Valutazione;
	}
	public void setValutazione(int valutazione) {
		Valutazione = valutazione;
	}
	public String getTesto() {
		return Testo;
	}
	public void setTesto(String testo) {
		Testo = testo;
	}
	
	

}
