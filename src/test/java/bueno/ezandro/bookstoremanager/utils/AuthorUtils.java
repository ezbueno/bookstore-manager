package bueno.ezandro.bookstoremanager.utils;

import com.buenoezandro.bookstoremanager.dtos.AuthorDTO;
import com.buenoezandro.bookstoremanager.entities.Author;
import com.github.javafaker.Faker;

public class AuthorUtils {

	private static final Faker faker = Faker.instance();

	public static AuthorDTO createFakeAuthorDTO() {
		return AuthorDTO.builder().id(faker.number().randomNumber()).name(faker.book().author())
				.age(faker.number().numberBetween(0, 100)).build();
	}

	public static Author createFakeAuthor() {
		return Author.builder().id(faker.number().randomNumber()).name(faker.book().author())
				.age(faker.number().numberBetween(0, 100)).build();
	}

	public static Author createFakeAuthorFrom(AuthorDTO authorDTO) {
		return Author.builder().id(authorDTO.getId()).name(authorDTO.getName()).age(authorDTO.getAge()).build();
	}

}
