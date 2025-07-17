package com.practica.library.repository;

import com.practica.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryJPA extends JpaRepository<Book, Long> {
}
