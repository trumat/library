package com.crud.library.repository;

import com.crud.library.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentRepositoryTest {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private BookCopyRepository bookCopyRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldSaveRent() {
        //Given
        Book book = new Book("Test title", "Test author", 1999);
        bookRepository.save(book);

        BookCopy bookCopy = new BookCopy(BookStatus.AVAILABLE, book);
        bookCopyRepository.save(bookCopy);

        Reader reader = new Reader("John", "Smith");
        readerRepository.save(reader);

        Rent rent = new Rent(bookCopy, reader);

        //When
        rentRepository.save(rent);
        Rent retrievedRent = rentRepository.findOne(1);

        //Then
        assertEquals(BookStatus.AVAILABLE, retrievedRent.getBookCopy().getStatus());
        assertEquals("Smith", retrievedRent.getReader().getLastname());
    }
}