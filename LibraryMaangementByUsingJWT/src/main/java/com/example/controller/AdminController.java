package com.example.controller;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookRepository bookRepo;

    // 🟢 Add a new book
    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book) {
        bookRepo.save(book);
        return "Book added successfully: " + book.getBookName();
    }

    // 🟣 Update an existing book
    @PutMapping("/updateBook/{id}")
    public String updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
        return bookRepo.findById(id).map(book -> {
            book.setBookName(updatedBook.getBookName());
            book.setAuthor(updatedBook.getAuthor());
            book.setBookPrice(updatedBook.getBookPrice());
            bookRepo.save(book);
            return "Book updated successfully.";
        }).orElse("Book not found with id: " + id);
    }

    // 🔴 Delete a book
    @DeleteMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable Integer id) {
        if (bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return "Book deleted successfully.";
        } else {
            return "Book not found with id: " + id;
        }
    }

    // 🔵 View all books
    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }
}
