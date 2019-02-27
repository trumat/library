package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldSaveBook() {
        //Given
        Book book = new Book("Test title", "Test author", 1999);
        //When
        bookRepository.save(book);
        int id = book.getId();
        Book retrievedBook = bookRepository.findOne(id);
        //Then
        assertEquals(1999, retrievedBook.getPubYear());
    }
}