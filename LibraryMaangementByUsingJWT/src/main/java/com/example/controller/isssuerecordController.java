package com.example.controller;

import com.example.entity.IssuseRecord;
import com.example.service.IssuseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class isssuerecordController {

    @Autowired
    private IssuseService issuseService;

    // 🟢 Issue a book
    @PostMapping("/book/{userId}/{bookId}")
    public String issueBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        return issuseService.issueBook(userId, bookId);
    }

    // 🟡 Return a book
    @PutMapping("/return/{issueId}")
    public String returnBook(@PathVariable Integer issueId) {
        return issuseService.returnBook(issueId);
    }

    // 🔵 View all records
    @GetMapping("/all")
    public List<IssuseRecord> getAllRecords() {
        return issuseService.getAllRecords();
    }
}
