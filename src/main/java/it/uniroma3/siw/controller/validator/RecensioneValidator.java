package it.uniroma3.siw.controller.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Objects;

import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;
@Component
public class RecensioneValidator implements Validator {
	@Autowired
	private RecensioneRepository recensioneRepository;
	@Override
	
	public void validate(Object o, Errors errors) {
		Recensione recensione = (Recensione)o;
		if(recensioneRepository.existsByUtente_IdAndFilm_Id(recensione.getUtente().getId(),recensione.getFilm().getId())) {
			errors.reject("recensione.duplicate");
		}
		
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Recensione.class.equals(aClass);
	}
}
