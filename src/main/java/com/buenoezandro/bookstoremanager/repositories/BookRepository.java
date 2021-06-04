package com.buenoezandro.bookstoremanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buenoezandro.bookstoremanager.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
