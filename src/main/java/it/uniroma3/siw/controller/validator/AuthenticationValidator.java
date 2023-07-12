package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siw.model.*;
import it.uniroma3.siw.repository.*;
import it.uniroma3.siw.model.Movie;
@Component
public class AuthenticationValidator implements Validator{
	@Autowired
	private CredentialsRepository credentialsRepository;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Credentials.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		// TODO Auto-generated method stub
		Credentials credentials = (Credentials)o;
		if (this.credentialsRepository.existsByUsername(credentials.getUsername())){
			errors.reject("credentials.username.duplicate");
		}
	}

}
