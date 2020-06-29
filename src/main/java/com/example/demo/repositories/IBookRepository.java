package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.demo.models.Book;

public interface IBookRepository
    extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book> {

    
}
