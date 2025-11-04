package com.example.controller;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addBook")
    public String addString(@RequestBody Book book){
        bookRepository.save(book);
        return " Book Added Successufully" ;

    }

    @GetMapping("/AllBooks")
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }


    @GetMapping("/findById/{id}")
    public String  findById(@PathVariable Integer id){
         Optional<Book> book =   bookRepository.findById(id);
        if(book.isPresent()){
            return  book.get().toString() ;

        }else{
            return  "bOOK IS NOT FOUND  "+id;
        }

    }

    @PutMapping("/updateBook")
    public String updateBook(@RequestBody Book book){
        bookRepository.save(book);
        return "Book Updated Suucresfully";
    }

    @DeleteMapping("/delete/{id}")
    public String  deleteBookbyId(@PathVariable Integer id){
           Optional<Book> book = bookRepository.findById(id);
           if(book.isPresent()){
                bookRepository.deleteById(id);
               return   " Book is deleted Succesufully";
           }else{
               return   " Book is deleted Succesufully";
           }


    }
    @DeleteMapping("/DeleteAll")
    public String  deleteBook(){
        bookRepository.deleteAll();
        return "All Books Are Deleted Succesufully";
    }

}
