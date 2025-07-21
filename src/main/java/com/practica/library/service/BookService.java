package com.practica.library.service;

import java.util.Random;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica.library.model.Book;
import com.practica.library.model.BookDTO;
import com.practica.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BookService {

    Logger logger = Logger.getLogger("BookService");
    private final BookRepository bookRepository;
    private final ObjectMapper mapper;


    public BookService(BookRepository bookRepository, ObjectMapper mapper) {

        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<BookDTO> getBooks() {

        List<Book> books = bookRepository.getBooks();
        List<Book> filteredBooks = new ArrayList<>();

        for (Book book: books) {
            if (book.getId() < 1000L) {
                logger.info("book " + book.getName() + " was added to the final list.");
                filteredBooks.add(book);
            }
        }

        return filteredBooks
                .stream()
                .map(book -> mapper.convertValue(book, BookDTO.class))
                .toList();
    }

    public void addBook(Book book){
        bookRepository.addBook(book);
    }

    public boolean deleteBook(Long id){
        return bookRepository.deleteBook(id);
    }

    public boolean updateBook(Long id, BookDTO book){
        return bookRepository.updateBook(id,mapper.convertValue(book,Book.class));
    }


    public String getRandomBookTitle() {
        List<Book> allBooks = bookRepository.getBooks(); // evităm findAll()

        if (allBooks.isEmpty()) {
            List<String> predefinedTitles = List.of(
                    "The Quantum Enigma",
                    "Lost in Time",
                    "Whispers of the Forgotten",
                    "The Silent Code",
                    "Journey to Nowhere"
            );

            for (String title : predefinedTitles) {
                Book book = new Book(title, "Unknown Author");
                bookRepository.save(book);
            }

            allBooks = bookRepository.getBooks();
        }

        int index = new Random().nextInt(allBooks.size());
        return allBooks.get(index).getName();
    }

}