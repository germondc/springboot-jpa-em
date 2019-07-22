package test.clyde.jpatest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaTestApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(JpaTestApplication.class);
	
	@Autowired
	MovieRepositoryImpl movieRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");
		test();
		test1();
		test2();
	}

	private Long id;
	
	private void test() {	     
	    Movie movie = new Movie();
	    movie.setMovieName("The Godfather");
	    movie.setReleaseYear(1972);
	    movie.setLanguage("English");
	    //id = movieRepo.insertMovie(movie);
	    movieRepo.saveMovie(movie);
	    id = movie.getId();
	    System.out.println("id: " + id);
	}
	
	private void test1() {
		Movie movie = movieRepo.getById(id);
		System.out.println(movie.getLanguage());
	}
	
	private void test2() {
		Movie movie = movieRepo.getById(id);
		movie.setMovieName("The Godfather 2");
		Movie savedMovie = movieRepo.saveMovie(movie);
		System.out.println(savedMovie);
	}
}
