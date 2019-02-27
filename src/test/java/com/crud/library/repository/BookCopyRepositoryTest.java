package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookCopyRepositoryTest {
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldSaveBookCopy() {
        //Given
        Book book = new Book("Test title", "Test author", 1999);
        bookRepository.save(book);
        BookCopy bookCopy = new BookCopy(BookStatus.AVAILABLE, book);
        //When
        bookCopyRepository.save(bookCopy);
        int id = bookCopy.getId();
        BookCopy retrievedBookCopy = bookCopyRepository.findOne(id);
        //Then
        assertEquals(BookStatus.AVAILABLE, retrievedBookCopy.getStatus());
        assertEquals("Test title", retrievedBookCopy.getBook().getTitle());
    }
}