package com.buenoezandro.bookstoremanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buenoezandro.bookstoremanager.dto.MessageResponseDTO;
import com.buenoezandro.bookstoremanager.entities.Book;
import com.buenoezandro.bookstoremanager.repositories.BookRepository;
import com.buenoezandro.bookstoremanager.util.MessageUtils;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public MessageResponseDTO create(Book book) {
		Book savedBook = this.bookRepository.save(book);
		return MessageResponseDTO.builder().message(MessageUtils.CREATED + savedBook.getId()).build();
	}

}
