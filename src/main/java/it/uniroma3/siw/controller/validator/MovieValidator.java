package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.MovieRepository;

@Component
public class MovieValidator implements Validator {
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public void validate(Object o, Errors errors) {
		Movie movie = (Movie)o;
		if (movie.getTitle()!=null && movie.getYear()!=null 
				&& movieRepository.existsByTitleAndYear(movie.getTitle(), movie.getYear())) {
			errors.reject("movie.duplicate");
		}
		else if (!movie.getFile().getContentType().equals("image/jpeg")) {
			errors.reject("movie.notjpeg");
		}
	}
	@Override
	public boolean supports(Class<?> aClass) {
		return Movie.class.equals(aClass);
	}
}