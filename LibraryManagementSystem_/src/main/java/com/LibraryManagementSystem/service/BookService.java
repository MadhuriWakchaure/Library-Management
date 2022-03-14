package com.LibraryManagementSystem.service;

import com.LibraryManagementSystem.infrastructure.entity.Book;
import com.LibraryManagementSystem.model.BookDTO;

public interface BookService {
    public Book saveBook(BookDTO bookDTO);
}
