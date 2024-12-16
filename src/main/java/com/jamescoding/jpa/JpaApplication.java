package com.jamescoding.jpa;

import com.github.javafaker.Faker;
import com.jamescoding.jpa.models.Author;
import com.jamescoding.jpa.models.Video;
import com.jamescoding.jpa.repositories.AuthorRepository;
import com.jamescoding.jpa.repositories.VideoRepository;
import com.jamescoding.jpa.specification.AuthorSpecification;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthorRepository repository,
			VideoRepository videoRepository
	) {
		return args -> {

			for(int i =0; i < 50;i++){
			Faker faker  = new Faker();
				var author = Author.builder()
						.firstName(faker.name().firstName())
						.lastName(faker.name().lastName())
						.age(faker.number().numberBetween(19, 50))
						.email("james" + i + "mbere@gmail.com")
						.build();
				//repository.save(author);
			}

			//update author with ID = 1
			var author = Author.builder()
					.id(1)
					.firstName("james")
					.lastName("mbere")
					.age(28)
					.email("jamesmbere@gmail.com")
					.build();
			//repository.save(author);

			// update Author a set a.age = 22 where a.id = 1
			//repository.updateAuthor(100,1);


			//update all authors
			//repository.updateAllAuthorsAges(99);

			//find by namedQueries
			/*repository.findByNamedQuery(60)
					.forEach(System.out::println);*/

			//update with named query
			//repository.updateByNamedQuery(12);

			Specification<Author> spec = Specification
					.where(AuthorSpecification.hasAge(43))
					.and(AuthorSpecification.firstnameLike("Mi"));
			repository.findAll(spec).forEach(System.out::println);
		};
	}

}
