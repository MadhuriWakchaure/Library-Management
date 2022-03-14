package com.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.infrastructure.repository.BookTrackorRepository;
import com.LibraryManagementSystem.model.BookDTO;
import com.LibraryManagementSystem.model.BookTrackorDTO;
import com.LibraryManagementSystem.service.Impl.BookServiceImpl;
import com.LibraryManagementSystem.service.Impl.BookTrackorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/booktrackor")

public class BookTrackorController {
    private BookTrackorServiceImpl bookTrackorServiceImpl;
    private BookServiceImpl bookServiceImpl;
    private BookTrackorRepository bookTrackorRepository;

    @Autowired(required = true)
    public void BookTrackorController(BookTrackorServiceImpl bookTrackorServiceImpl,BookServiceImpl bookServiceImpl,BookTrackorRepository bookTrackorRepository){
        this.bookTrackorServiceImpl=bookTrackorServiceImpl;
        this.bookServiceImpl=bookServiceImpl;
        this.bookTrackorRepository=bookTrackorRepository;
    }
    @PostMapping("/insert/bookInfo")
    private ResponseEntity<?>insertBookTrackor(@RequestBody BookTrackorDTO bookTrackorDTO){
        Map<String,Object> map=new HashMap<>();
        map.put("booktrackor",bookTrackorServiceImpl.savebooktrackordetails(bookTrackorDTO));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PutMapping("/update/booktrackor/{id}")
    private ResponseEntity<?> updateBookTrackor(@PathVariable Long id,@RequestBody BookTrackorDTO bookTrackorDTO){
        Map<String,Object> map=new HashMap<>();
        map.put("booktrackor",bookTrackorServiceImpl.saveorupdatebooktrackor(id,bookTrackorDTO));
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


}
