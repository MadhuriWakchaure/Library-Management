package com.LibraryManagementSystem.infrastructure.repository;

import com.LibraryManagementSystem.infrastructure.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
//    @Query(value="SELECT * FROM book WHERE bookId LIKE %?1% AND name Like %?2% AND status=0 OR taken_by=%?2% AND status=0 ORDER BY id DESC",nativeQuery = true)
//    List<Book> findBybookId(String bookId, String keyword);
@Query(value = "SELECT * from book where book_id = ?1",nativeQuery = true)
Book findByBookId(Long bookId);

    boolean existsByBookId(long bookId);



}
