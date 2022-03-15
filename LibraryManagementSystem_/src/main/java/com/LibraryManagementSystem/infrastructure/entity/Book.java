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
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String bookId;
    private String takenBy;
    private Long copies;
    private Long personId;
    private Date returnDate;
    private Date takenDate;
    private boolean isAvailable;
    private int status;


//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "person_id",nullable = false, insertable = false, updatable = false)
//    @JsonIgnore
//    private BookTrackor bookTrackor;
}
