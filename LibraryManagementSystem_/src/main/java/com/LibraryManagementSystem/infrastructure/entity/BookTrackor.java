package com.LibraryManagementSystem.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="booktrackor")
public class BookTrackor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long personId;
    private String department;
    private Date takenDate;
    private Date returnDate;
    private String comment;
    private int status;
    private String bookId;
//    private long book_id;


//@OneToMany(mappedBy = "booktrackor", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//public List<Book>book;


}
