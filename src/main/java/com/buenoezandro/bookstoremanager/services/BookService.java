package com.buenoezandro.bookstoremanager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buenoezandro.bookstoremanager.dtos.BookDTO;
import com.buenoezandro.bookstoremanager.dtos.MessageResponseDTO;
import com.buenoezandro.bookstoremanager.entities.Book;
import com.buenoezandro.bookstoremanager.exceptions.IntegrityViolationException;
import com.buenoezandro.bookstoremanager.mapper.BookMapper;
import com.buenoezandro.bookstoremanager.repositories.BookRepository;
import com.buenoezandro.bookstoremanager.util.MessageUtils;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	private final BookMapper bookMapper = BookMapper.INSTANCE;

	public MessageResponseDTO create(BookDTO bookDTO) {
		Book bookToSave = bookMapper.toModel(bookDTO);
		
		Optional<Book> findAuthorName = this.bookRepository.findByName(bookToSave.getAuthor().getName());
		
		if (findAuthorName.isPresent()) {
			throw new IntegrityViolationException(MessageUtils.NAME_ALREADY_EXISTS);
		}
		
		Book savedBook = this.bookRepository.save(bookToSave);
		return MessageResponseDTO.builder().message(MessageUtils.CREATED + savedBook.getId()).build();
	}

}
