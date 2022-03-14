package com.LibraryManagementSystem.infrastructure.repository;

import com.LibraryManagementSystem.infrastructure.entity.BookTrackor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTrackorRepository extends JpaRepository<BookTrackor,Long> {
    @Query(value="SELECT * FROM booktrackor order by id desc",nativeQuery = true)
    List<BookTrackor> getAllbook();
}
