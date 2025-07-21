package com.practica.library.repository;

import com.practica.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import com.practica.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    Logger logger = Logger.getLogger("BookRepository");
    private final List<Book> books = new ArrayList<>();
    private Long id = 0L;

    public BookRepository(){
        Book book1 = new Book(++id, "Inteligenta emotionala", "Daniel Hoffman");
        Book book2 = new Book(++id, "Bani", "Daniel McWilliams");
        books.add(book1);
        books.add(book2);
    }
    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book){
        if(book.getId() == null)
            book.setId(++id);
        books.add(book);
    }

    public boolean deleteBook(Long id){
        return books.removeIf(book -> book.getId().equals(id));
    }

    public boolean updateBook(Long id, Book book){
        Optional<Book> first = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
        if(first.isPresent()){
            logger.info("updating book with ID " + id);
            Optional.ofNullable(book.getName()).ifPresent( name -> first.get().setName(name));
            Optional.ofNullable(book.getAuthor()).ifPresent( author -> first.get().setAuthor(author));
            return true;
        }
        return false;
    }
    public List<Book> getAllBooks() {
        return books;
    }

    public void save(Book book) {
    }
}
