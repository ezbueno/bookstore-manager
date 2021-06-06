package com.buenoezandro.bookstoremanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.bookstoremanager.dtos.BookDTO;
import com.buenoezandro.bookstoremanager.dtos.MessageResponseDTO;
import com.buenoezandro.bookstoremanager.entities.Book;
import com.buenoezandro.bookstoremanager.exceptions.IntegrityViolationException;
import com.buenoezandro.bookstoremanager.exceptions.BookNotFoundException;
import com.buenoezandro.bookstoremanager.mapper.BookMapper;
import com.buenoezandro.bookstoremanager.repositories.BookRepository;
import com.buenoezandro.bookstoremanager.util.MessageUtils;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	private static final BookMapper bookMapper = BookMapper.INSTANCE;

	@Transactional
	public MessageResponseDTO create(BookDTO bookDTO) {
		var bookToSave = bookMapper.toModel(bookDTO);

		Optional<Book> authorName = this.bookRepository.findAuthorByName(bookToSave.getAuthor().getName());

		if (authorName.isPresent()) {
			throw new IntegrityViolationException(MessageUtils.NAME_ALREADY_EXISTS);
		}

		var savedBook = this.bookRepository.save(bookToSave);
		return MessageResponseDTO.builder().message(MessageUtils.CREATED + savedBook.getId()).build();
	}

	@Transactional(readOnly = true)
	public BookDTO findById(Long id) {
		var book = this.findBookById(id);
		return bookMapper.toDTO(book);
	}

	private Book findBookById(Long id) {
		var optionalBook = this.bookRepository.findById(id);

		if (optionalBook.isPresent()) {
			return optionalBook.get();
		}

		throw new BookNotFoundException(MessageUtils.BOOK_NOT_FOUND + id);

	}

	@Transactional(readOnly = true)
	public List<BookDTO> findAll() {
		var books = this.bookRepository.findAll();
		return bookMapper.toDTO(books);
	}

}
