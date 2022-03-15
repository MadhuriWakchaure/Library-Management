package com.LibraryManagementSystem.controller;

import com.LibraryManagementSystem.model.BookDTO;
import com.LibraryManagementSystem.service.Impl.BookServiceImpl;
import com.LibraryManagementSystem.service.Impl.BookTrackorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/Book")
public class BookController {
    private BookServiceImpl bookServiceImpl;
    private BookTrackorServiceImpl bookTrackorServiceImpl;
   @Autowired(required = true)
    public void BookController(BookServiceImpl bookServiceImpl,BookTrackorServiceImpl bookTrackorServiceImpl){
       this.bookServiceImpl=bookServiceImpl;
       this.bookTrackorServiceImpl=bookTrackorServiceImpl;
   }
@PostMapping("/insert/book")
    private ResponseEntity<?> insertBook(@RequestBody BookDTO bookDTO){
   Map<String,Object> map=new HashMap<>();
map.put("book",bookServiceImpl.saveBook(bookDTO));
return new ResponseEntity<>(map, HttpStatus.OK);
}

@PutMapping("/update/book/{id}")
 private ResponseEntity<?> updateBook(@PathVariable Long id,@RequestBody BookDTO bookDTO){
       Map<String,Object> map=new HashMap<>();
       map.put("book",bookServiceImpl.saveorupdateBook(id,bookDTO));
       return new ResponseEntity<>(map,HttpStatus.OK);
}
@GetMapping("/get/book")
    private ResponseEntity<?> getbook(){
       Map<String,Object> map=new HashMap<>();
       map.put("book",bookServiceImpl.getAllbookList());
       return new ResponseEntity<>(map,HttpStatus.OK);
}
@GetMapping("/get/book/{id}")
    private ResponseEntity<?> getbookById(@PathVariable Long id){
       Map<String,Object>map=new HashMap<>();
       map.put("book",bookServiceImpl.getbook(id));
       return new ResponseEntity<>(map,HttpStatus.OK);
}
@GetMapping("/search/book")
    private ResponseEntity<?> getBookbysearch(@RequestParam String keyword){
        Map<String,Object>map=new HashMap<>();
//    keyword = keyword.replaceAll("[0-9.'\"]+", "").trim();
        map.put("book",bookServiceImpl.getbooklist(keyword));
        log.info("Search {} successfully fetched from databse",keyword);

        return new ResponseEntity<>(map,HttpStatus.OK);
}



}
