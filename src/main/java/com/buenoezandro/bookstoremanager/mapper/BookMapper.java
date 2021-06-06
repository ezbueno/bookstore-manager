package com.buenoezandro.bookstoremanager.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.buenoezandro.bookstoremanager.dtos.BookDTO;
import com.buenoezandro.bookstoremanager.entities.Book;

@Mapper
public interface BookMapper {

	BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
	Book toModel(BookDTO bookDTO);
	BookDTO toDTO(Book book);
	List<BookDTO> toDTO(List<Book> books);
	
}
