package com.LibraryManagementSystem.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class BookTrackorDTO {
    private Long id;
    private String name;
    private long personId;
    private String department;
    private Date takenDate;
    private Date returnDate;
    private String comment;
    private Long bookId;
    private int status;

}
