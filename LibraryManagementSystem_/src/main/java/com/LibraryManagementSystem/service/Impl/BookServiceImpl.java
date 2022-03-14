package com.LibraryManagementSystem.service.Impl;

import com.LibraryManagementSystem.infrastructure.entity.Book;
import com.LibraryManagementSystem.infrastructure.entity.BookTrackor;
import com.LibraryManagementSystem.infrastructure.repository.BookRepository;
import com.LibraryManagementSystem.infrastructure.repository.BookTrackorRepository;
import com.LibraryManagementSystem.model.BookDTO;
import com.LibraryManagementSystem.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    private BookTrackorRepository bookTrackorRepository;


    @Autowired
    public void BookServiceImpl(BookRepository bookRepository, BookTrackorRepository bookTrackorRepository) {
        this.bookRepository = bookRepository;
        this.bookTrackorRepository = bookTrackorRepository;

    }

    public Book saveBook(BookDTO bookDTO) {
        Date date = new Date();

        Book book = Book.builder()
                .id(bookDTO.getId())
                .name(bookDTO.getName())
                .bookId(bookDTO.getBookId())
                .personId(bookDTO.getPersonId())
                .returnDate(bookDTO.getReturnDate())
                .isAvailable(bookDTO.isAvailable())
                .status(bookDTO.getStatus())
                .takenBy(bookDTO.getTakenBy())
                .takenDate(bookDTO.getTakenDate())
                .build();
        return bookRepository.save(book);
    }

    public Book saveorupdateBook(Long id, BookDTO bookDTO) {
        if (bookRepository.existsById(id)) {
            Date date = new Date();

            long taken_date = date.getTime();

            Book book = bookRepository.findById(id).get();
            book.setName(bookDTO.getName());
            book.setBookId(bookDTO.getBookId());
            book.setPersonId(bookDTO.getPersonId());
            book.setAvailable(bookDTO.isAvailable());
            book.setTakenBy(bookDTO.getTakenBy());
            book.setTakenDate(new Date(taken_date));
            book.setStatus(bookDTO.getStatus());
            return bookRepository.save(book);
        } else {
            return null;
        }
    }


    public List<Book> getAllbookList() {
        return bookRepository.findAll();
    }

    public Book getbook(Long id) {
        return bookRepository.findById(id).get();
    }

//    public List<Book> getbooklist(String bookId, String keyword) {
//        return bookRepository.findById()d(bookId,keyword.trim());
//
//    }
}
