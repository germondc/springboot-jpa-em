package test.clyde.jpatest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class MovieRepositoryImpl {

	private final EntityManager em;

	@Autowired
	public MovieRepositoryImpl(JpaContext context) {
		this.em = context.getEntityManagerByManagedType(Movie.class);
	}
	
	public Movie getById(long id) {
		Movie movie = em.find(Movie.class, id);
		return movie;
	}
	
	public Long insertMovie(Movie movie) {
		em.persist(movie);
		return movie.getId();
	}
	
	public Movie saveMovie(Movie movie) {
		if (movie.getId() == null) {
			insertMovie(movie);
			return movie;
		}
		return em.merge(movie);
	}
}
