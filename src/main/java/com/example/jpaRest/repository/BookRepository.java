package com.example.jpaRest.repository;

import com.example.jpaRest.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    List<Book> findByTitleContainingOrDescriptionContaining(String searchTerm, String searchTerm1);
}
