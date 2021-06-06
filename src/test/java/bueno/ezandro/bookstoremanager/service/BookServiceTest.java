package bueno.ezandro.bookstoremanager.service;

import static bueno.ezandro.bookstoremanager.utils.BookUtils.createFakeBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.buenoezandro.bookstoremanager.dtos.BookDTO;
import com.buenoezandro.bookstoremanager.entities.Book;
import com.buenoezandro.bookstoremanager.exceptions.BookNotFoundException;
import com.buenoezandro.bookstoremanager.repositories.BookRepository;
import com.buenoezandro.bookstoremanager.services.BookService;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookService bookService;

	@Test
	void whenGivenExistingIdThenReturnThisBook() throws BookNotFoundException {
		Book expectedFoundBook = createFakeBook();

		when(bookRepository.findById(expectedFoundBook.getId())).thenReturn(Optional.of(expectedFoundBook));

		BookDTO bookDTO = bookService.findById(expectedFoundBook.getId());

		assertEquals(expectedFoundBook.getName(), bookDTO.getName());
		assertEquals(expectedFoundBook.getIsbn(), bookDTO.getIsbn());
		assertEquals(expectedFoundBook.getPublisherName(), bookDTO.getPublisherName());
	}

	@Test
	void whenGivenUnexistingIdThenNotFindThrowAnException() {
		var invalidId = 10L;

		when(bookRepository.findById(invalidId)).thenReturn(Optional.ofNullable(any(Book.class)));

		assertThrows(BookNotFoundException.class, () -> bookService.findById(invalidId));
	}

}
