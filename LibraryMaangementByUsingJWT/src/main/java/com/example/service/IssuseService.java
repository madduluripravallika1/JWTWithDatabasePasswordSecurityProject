package com.example.service;

import com.example.entity.Book;
import com.example.entity.IssuseRecord;
import com.example.entity.User;
import com.example.repository.BookRepository;
import com.example.repository.IssuseRecordRepositort;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IssuseService {

    @Autowired
    private IssuseRecordRepositort issuseRecordRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRepository userRepo;

    // 🟢 Issue a book
    public String issueBook(Integer userId, Integer bookId) {
        Optional<User> userOpt = userRepo.findById(userId);
        Optional<Book> bookOpt = bookRepo.findById(bookId);

        if (userOpt.isEmpty() || bookOpt.isEmpty()) {
            return "User or Book not found!";
        }

        IssuseRecord record = new IssuseRecord();
        record.setIssuseDate(LocalDate.now().toString());
        record.setDueDate(LocalDate.now().plusDays(14).toString()); // due in 14 days
        record.setReturn(false);
        record.setBook(bookOpt.get());
        record.setUser(userOpt.get());

        issuseRecordRepo.save(record);
        return "Book issued successfully to " + userOpt.get().getUsername();
    }

    // 🟡 Return book
    public String returnBook(Integer issueId) {
        Optional<IssuseRecord> recordOpt = issuseRecordRepo.findById(issueId);

        if (recordOpt.isEmpty()) {
            return "Issue record not found!";
        }

        IssuseRecord record = recordOpt.get();
        record.setReturndate(LocalDate.now().toString());
        record.setReturn(true);

        issuseRecordRepo.save(record);
        return "Book returned successfully!";
    }

    // 🔵 Get all issued records
    public List<IssuseRecord> getAllRecords() {
        return issuseRecordRepo.findAll();
    }
}
