package com.buenoezandro.bookstoremanager.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.bookstoremanager.dtos.BookDTO;
import com.buenoezandro.bookstoremanager.dtos.MessageResponseDTO;
import com.buenoezandro.bookstoremanager.services.BookService;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResponseDTO create(@RequestBody @Valid BookDTO bookDTO) {
		return this.bookService.create(bookDTO);
	}

}
