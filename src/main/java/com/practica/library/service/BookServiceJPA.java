package com.practica.library.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica.library.model.Book;
import com.practica.library.model.BookDTO;
import com.practica.library.repository.BookRepositoryJPA;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookServiceJPA {

    private final BookRepositoryJPA bookRepositoryJPA;
    private final ObjectMapper mapper;

    public BookServiceJPA(BookRepositoryJPA bookRepositoryJPA, ObjectMapper mapper) {
        this.bookRepositoryJPA = bookRepositoryJPA;
        this.mapper = mapper;
    }

    public List<BookDTO> getBooks() {

        return bookRepositoryJPA.findAll()
                .stream()
                .map(book -> mapper.convertValue(book, BookDTO.class))
                .toList();
    }

    public BookDTO saveBook(Book book) {

        return mapper.convertValue(bookRepositoryJPA.save(book), BookDTO.class);
    }
    public String getRandomBookTitle() {
        List<Book> allBooks = bookRepositoryJPA.getAllBooks();

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
                bookRepositoryJPA.save(book);
            }

            allBooks = bookRepositoryJPA.getAllBooks();
        }

        int index = new Random().nextInt(allBooks.size());
        return allBooks.get(index).getName();
    }
}
