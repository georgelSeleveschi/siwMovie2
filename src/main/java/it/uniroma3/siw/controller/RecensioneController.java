package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.RecensioneValidator;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Recensione;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.MovieRepository;
import it.uniroma3.siw.repository.RecensioneRepository;
import it.uniroma3.siw.repository.UserRepository;
import it.uniroma3.siw.service.CredentialsService;

@Controller
public class RecensioneController {
	@Autowired 
	private MovieRepository movieRepository;
	@Autowired 
	private CredentialsService credentialsService;
	@Autowired
	private RecensioneRepository recensioneRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private RecensioneValidator recensioneValidator;
	@GetMapping("/recensioni/{user}")
	public String Recensioni(Model model,@PathVariable("user") String user) {
		model.addAttribute("recensioni",recensioneRepository.findAllByUtente(this.credentialsService.getCredentials(user).getUser()));
		return "Recensioni.html";
	}
	@GetMapping("/movie/newRecensione/{user}/{idmovie}")
	public String newRecensione(Model model,@PathVariable("user") String user,@PathVariable("idmovie")Long idmov) {
		model.addAttribute("recensione",new Recensione());
		model.addAttribute("user",user);
		model.addAttribute("idmovie",idmov);
		return "formNewRecensione.html";
	}
	
	@Transactional
	@PostMapping("/recensione/{idmovie}/{user}")
	public String saveRecensione(@Valid @ModelAttribute("recensione") Recensione recensione,BindingResult bindingResult,@PathVariable("user")String user,@PathVariable("idmovie")Long idmov) {
		
		
		User utente= this.credentialsService.getCredentials(user).getUser();
		Movie film=this.movieRepository.findById(idmov).get();
		recensione.setFilm(film);
		recensione.setUtente(utente);
		this.recensioneValidator.validate(recensione, bindingResult);
		if (!bindingResult.hasErrors()) {
		film.getRecensioni().add(recensione);
		utente.getRecensioni().add(recensione);
		this.userRepository.save(utente);
		this.movieRepository.save(film);
		this.recensioneRepository.save(recensione);
		return ("RecensioneConferma.html");
		}
		else {
			return ("formNewRecensione.html");
		}
	}
	@GetMapping("/admin/EliminaRecensione/{idRecensione}/{idmovie}")
	public String elimina (Model model,@PathVariable("idRecensione")Long idr,@PathVariable("idmovie")Long idmov) {
		this.recensioneRepository.deleteById(idr);
		return ("RecensioneConferma.html");
	}
}
