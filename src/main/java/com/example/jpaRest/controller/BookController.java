package com.example.jpaRest.controller;

import com.example.jpaRest.entity.Book;
import com.example.jpaRest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;


    @GetMapping()
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable int id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping()
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }


    @PutMapping("/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        // getting book
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }

}
