package com.LibraryManagementSystem.service.Impl;

import com.LibraryManagementSystem.infrastructure.entity.Book;
import com.LibraryManagementSystem.infrastructure.entity.BookTrackor;
import com.LibraryManagementSystem.infrastructure.repository.BookRepository;
import com.LibraryManagementSystem.infrastructure.repository.BookTrackorRepository;
import com.LibraryManagementSystem.model.BookTrackorDTO;
import com.LibraryManagementSystem.service.BookTrackorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class BookTrackorServiceImpl implements BookTrackorService {

    private BookTrackorRepository bookTrackorRepository;
    private BookRepository bookRepository;



    @Autowired
    public void BookTrackorServiceImpl(BookTrackorRepository bookTrackorRepository,BookRepository bookRepository){
        this.bookTrackorRepository=bookTrackorRepository;
        this.bookRepository=bookRepository;
    }


    public BookTrackor savebooktrackordetails(BookTrackorDTO bookTrackorDTO) {
        Date date = new Date();
        long taken_date = date.getTime();
        BookTrackor bookTrackor=BookTrackor.builder()
                .id(bookTrackorDTO.getId())
                .name(bookTrackorDTO.getName())
                .personId(bookTrackorDTO.getPersonId())
                .department(bookTrackorDTO.getDepartment())
                .bookId(bookTrackorDTO.getBookId())
                .takenDate(new Date(taken_date))
                .comment(bookTrackorDTO.getComment())
                .returnDate(bookTrackorDTO.getReturnDate())
                .status(bookTrackorDTO.getStatus())
                .build();
        bookTrackorRepository.save(bookTrackor);

        Book book = Book.builder()
                .id(bookTrackorDTO.getId())
                .bookId(bookTrackorDTO.getBookId())
                .personId(bookTrackorDTO.getPersonId())
                .returnDate(bookTrackorDTO.getReturnDate())
               .isAvailable(false)
                .status(bookTrackorDTO.getStatus())
                .takenBy(bookTrackorDTO.getName())
                .takenDate(new Date(taken_date))
                .build();
         bookRepository.save(book);
         return bookTrackor;

    }


    public BookTrackor saveorupdatebooktrackor(Long id,BookTrackorDTO bookTrackorDTO) {
        if (bookTrackorRepository.existsById(id)) {

            BookTrackor bookTrackor = bookTrackorRepository.findById(id).get();
            Date date = new Date();

            long taken_date = date.getTime();
            bookTrackor.setName(bookTrackorDTO.getName());
            bookTrackor.setPersonId(bookTrackorDTO.getPersonId());
            bookTrackor.setBookId(bookTrackorDTO.getBookId());
            bookTrackor.setDepartment(bookTrackorDTO.getDepartment());
            bookTrackor.setComment(bookTrackorDTO.getComment());
            bookTrackor.setReturnDate(bookTrackor.getReturnDate());
            bookTrackor.setTakenDate(new Date(taken_date));
            bookTrackor.setStatus(bookTrackorDTO.getStatus());


            if (bookRepository.existsByBookId(bookTrackor.getBookId())) {

//                 bookId = Long.valueOf((Long) bookId);



                Book book = bookRepository.findByBookId(bookTrackor.getBookId());
                long bookId = book.getBookId();
                log.error("Book id "+bookId);
                //            book.setName(bookDTO.getName());
               book.setBookId(bookTrackorDTO.getBookId());
                book.setPersonId(bookTrackorDTO.getPersonId());
//            book.setAvailable(bookTrackorDTO.isAvailable());
                book.setTakenBy(bookTrackorDTO.getName());
                book.setTakenDate(new Date(taken_date));
                book.setStatus(bookTrackorDTO.getStatus());
                bookRepository.save(book);
            }
            return bookTrackorRepository.save(bookTrackor);
        }
        return null;
    }

    public List<BookTrackor> getbookTrackordetails() {
        return bookTrackorRepository.findAll();
    }
}

