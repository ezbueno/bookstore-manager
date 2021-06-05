package com.buenoezandro.bookstoremanager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buenoezandro.bookstoremanager.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("SELECT book FROM Book book WHERE book.author.name = :name")
	Optional<Book> findByName(@Param(value = "name") String name);
}
