package com.practica.library.repository;

import com.practica.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepositoryJPA<T, ID> extends JpaRepository<T, ID> {
    default List<Book> getAllBooks() {
        List<Book> books = null;
        return books;
    }

    default Object save(Book book) {
        return null;
    }
}
