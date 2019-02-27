package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.dto.BookDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    public void mapToBookDto() {
        //Given
        Book book = new Book("title", "author", 1990);
        BookCopy bookCopy = new BookCopy(BookStatus.AVAILABLE, book);
        book.getBookCopies().add(bookCopy);

        //When
        BookDto mappedBookDto = bookMapper.mapToBookDto(book);

        //Then
        assertEquals(0, mappedBookDto.getId());
        assertEquals("title", mappedBookDto.getTitle());
    }

    @Test
    public void mapToBook() {
        //Given
        BookDto bookDto = new BookDto(1, "title", "author", 1990);

        //When
        Book mappedBook = bookMapper.mapToBook(bookDto);

        //Then
        assertEquals("title", mappedBook.getTitle());
    }

    @Test
    public void mapToBookDtos() {
        //Given
        Book book1 = new Book("title1", "author1", 1990);
        BookCopy bookCopy1 = new BookCopy(BookStatus.AVAILABLE, book1);
        book1.getBookCopies().add(bookCopy1);

        Book book2 = new Book("title2", "author2", 1991);
        BookCopy bookCopy2 = new BookCopy(BookStatus.UNAVAILABLE, book2);
        book2.getBookCopies().add(bookCopy2);

        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        //When
        List<BookDto> mappedBookDtos = bookMapper.mapToBookDtos(books);

        //Then
        assertEquals(1991, mappedBookDtos.get(1).getPubYear());
        assertEquals("author1", mappedBookDtos.get(0).getAuthor());
    }

    @Test
    public void mapToBooks() {
        //Given
        BookDto bookDto1 = new BookDto(1, "title1", "author1", 1990);
        BookDto bookDto2 = new BookDto(1, "title2", "author2", 1991);

        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(bookDto1);
        bookDtos.add(bookDto2);

        //When
        List<Book> mappedBooks = bookMapper.mapToBooks(bookDtos);

        //Then
        assertEquals("author2", mappedBooks.get(1).getAuthor());
        assertEquals(new ArrayList<>(), mappedBooks.get(0).getBookCopies());
    }
}