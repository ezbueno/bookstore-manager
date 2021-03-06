package bueno.ezandro.bookstoremanager.controller;

import static bueno.ezandro.bookstoremanager.utils.BookUtils.asJsonString;
import static bueno.ezandro.bookstoremanager.utils.BookUtils.createFakeBookDTO;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.buenoezandro.bookstoremanager.controllers.BookController;
import com.buenoezandro.bookstoremanager.dtos.BookDTO;
import com.buenoezandro.bookstoremanager.dtos.MessageResponseDTO;
import com.buenoezandro.bookstoremanager.services.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

	public static final String BOOK_API_URL_PATH = "/api/v1/books";

	private MockMvc mockMvc;

	@Mock
	private BookService bookService;

	@InjectMocks
	private BookController bookController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(bookController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setViewResolvers((viewName, locale) -> new MappingJackson2JsonView()).build();
	}

	@Test
	void testWhenPOSTisCalledThenABookShouldBeCreated() throws Exception {
		BookDTO bookDTO = createFakeBookDTO();
		MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
				.message("Book created with ID: " + bookDTO.getId()).build();

		when(bookService.create(bookDTO)).thenReturn(expectedMessageResponse);

		mockMvc.perform(post(BOOK_API_URL_PATH).contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookDTO)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
	}

	@Test
	void testWhenPOSTwithInvalidISBNIsCalledThenBadRequestShouldBeReturned() throws Exception {
		BookDTO bookDTO = createFakeBookDTO();
		bookDTO.setIsbn("Invalid ISBN");

		mockMvc.perform(post(BOOK_API_URL_PATH).contentType(MediaType.APPLICATION_JSON).content(asJsonString(bookDTO)))
				.andExpect(status().isBadRequest());
	}

}
