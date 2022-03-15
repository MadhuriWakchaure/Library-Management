package com.LibraryManagementSystem.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class BookDTO {
    private Long id;
    private String name;
    private String bookId;
    private Long copies;
    private String takenBy;
    private long personId;
    private Date returnDate;
    private Date takenDate;
    private boolean isAvailable;
    private int status;
}
