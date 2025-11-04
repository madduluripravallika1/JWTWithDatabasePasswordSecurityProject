package com.example.repository;

import com.example.entity.Book;
import com.example.entity.IssuseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuseRecordRepositort extends JpaRepository<IssuseRecord,Integer> {
}
