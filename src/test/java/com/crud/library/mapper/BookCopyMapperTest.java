package com.crud.library.mapper;

import com.crud.library.domain.*;
import com.crud.library.dto.BookCopyDto;
import com.crud.library.dto.BookDto;
import com.crud.library.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookCopyMapperTest {
    @InjectMocks
    private BookCopyMapper bookCopyMapper;
    @Mock
    private BookService bookService;

    @Test
    public void mapToBookCopyDto() {
        //Given
        Book book = new Book("title", "author", 1990);
        Reader reader = new Reader("John", "Smith");
        BookCopy bookCopy = new BookCopy(BookStatus.UNAVAILABLE, book);
        Rent rent = new Rent(bookCopy, reader);
        bookCopy.getRents().add(rent);

        //When
        BookCopyDto mappedBookCopyDto = bookCopyMapper.mapToBookCopyDto(bookCopy);

        //Then
        assertEquals(BookStatus.UNAVAILABLE, mappedBookCopyDto.getStatus());
        assertEquals(0, mappedBookCopyDto.getBookId());
    }

    @Test
    public void mapToBookCopy() {
        //Given
        Book book = new Book(2, "title", "author", 1990, new ArrayList<>());
        when(bookService.getBook(2)).thenReturn(book);
        BookCopyDto bookCopyDto = new BookCopyDto(1, BookStatus.AVAILABLE, 2);

        //When
        BookCopy mappedBookCopy = bookCopyMapper.mapToBookCopy(bookCopyDto);

        //Then
        assertEquals("title", mappedBookCopy.getBook().getTitle());
        assertEquals(BookStatus.AVAILABLE, mappedBookCopy.getStatus());
    }

    @Test
    public void mapToBookCopyDtos() {
        //Given
        Book book1 = new Book("title1", "author1", 1990);
        Reader reader1 = new Reader("John", "Smith");
        BookCopy bookCopy1 = new BookCopy(BookStatus.UNAVAILABLE, book1);
        Rent rent1 = new Rent(bookCopy1, reader1);
        bookCopy1.getRents().add(rent1);

        Book book2 = new Book("title2", "author2", 1991);
        Reader reader2 = new Reader("Michael", "Scott");
        BookCopy bookCopy2 = new BookCopy(BookStatus.UNAVAILABLE, book2);
        Rent rent2 = new Rent(bookCopy2, reader2);
        bookCopy2.getRents().add(rent2);

        List<BookCopy> bookCopies = new ArrayList<>();
        bookCopies.add(bookCopy1);
        bookCopies.add(bookCopy2);

        //When
        List<BookCopyDto> mappedBookCopyDtos = bookCopyMapper.mapToBookCopyDtos(bookCopies);

        //Then
        assertEquals(0, mappedBookCopyDtos.get(1).getBookId());
        assertEquals(BookStatus.UNAVAILABLE, mappedBookCopyDtos.get(0).getStatus());
    }
}