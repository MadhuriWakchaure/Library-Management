package com.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.infrastructure.entity.Book;
import com.LibraryManagementSystem.infrastructure.entity.BookTrackor;
import com.LibraryManagementSystem.infrastructure.repository.BookRepository;
import com.LibraryManagementSystem.infrastructure.repository.BookTrackorRepository;
import com.LibraryManagementSystem.model.BookDTO;
import com.LibraryManagementSystem.model.BookTrackorDTO;
import com.LibraryManagementSystem.service.Impl.BookServiceImpl;
import com.LibraryManagementSystem.service.Impl.BookTrackorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/booktrackor")

public class BookTrackorController {
    private BookTrackorServiceImpl bookTrackorServiceImpl;
    private BookServiceImpl bookServiceImpl;
    private BookTrackorRepository bookTrackorRepository;
    private BookRepository bookRepository;


    @Autowired(required = true)
    public void BookTrackorController(BookTrackorServiceImpl bookTrackorServiceImpl,BookServiceImpl bookServiceImpl,BookTrackorRepository bookTrackorRepository,BookRepository bookRepository){
        this.bookTrackorServiceImpl=bookTrackorServiceImpl;
        this.bookServiceImpl=bookServiceImpl;
        this.bookTrackorRepository=bookTrackorRepository;
        this.bookRepository=bookRepository;
    }
    @PostMapping("/insert/bookInfo")
    private ResponseEntity<?>insertBookTrackor(@RequestBody BookTrackorDTO bookTrackorDTO){
        Map<String,Object> map=new HashMap<>();
        map.put("booktrackor",bookTrackorServiceImpl.savebooktrackordetails(bookTrackorDTO));
        String bookid = bookTrackorDTO.getBookId();
        Book book = bookRepository.findBybookID(bookid);
        long copies=book.getCopies();
        if(copies<=0){
            return new ResponseEntity<>("book is not available",HttpStatus.OK);
        }
        else{
            copies=copies-1;
            book.setCopies(copies);
            bookRepository.save(book);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PutMapping("/update/booktrackor/{id}")
    private ResponseEntity<?> updateBookTrackor(@PathVariable Long id,@RequestBody BookTrackorDTO bookTrackorDTO){
        Map<String,Object> map=new HashMap<>();
        map.put("booktrackor",bookTrackorServiceImpl.saveorupdatebooktrackor(id,bookTrackorDTO));

        String bookid = bookTrackorDTO.getBookId();
        Book book = bookRepository.findBybookID(bookid);
        long copies=book.getCopies();


            copies=copies+1;
            book.setCopies(copies);
            bookRepository.save(book);

        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @GetMapping("/get/booktrackor")
    private ResponseEntity<?> getBookTrackor() {
        Map<String, Object> map = new HashMap<>();
        map.put("booktrackor", bookTrackorServiceImpl.getbookTrackordetails());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @GetMapping("/get/booktrackors")
    private ResponseEntity<?> getBookDatails() {
        Map<String, Object> map = new HashMap<>();
        map.put("booktrackor", bookTrackorRepository.getAllbook());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
   @GetMapping("/search/booktrackor")
    private ResponseEntity<?>getBooktrackorbysearch(@RequestParam String keyword){
        Map<String,Object>map=new HashMap<>();
        map.put("booktrackor",bookTrackorServiceImpl.getbooktrackorsearchlist(keyword));
        log.info("search successfully fetched from database",keyword);
        return new ResponseEntity<>(map,HttpStatus.OK);
   }

}
