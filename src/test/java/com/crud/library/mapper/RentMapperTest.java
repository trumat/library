package com.crud.library.mapper;

import com.crud.library.domain.*;
import com.crud.library.dto.BookCopyDto;
import com.crud.library.dto.BookDto;
import com.crud.library.dto.ReaderDto;
import com.crud.library.dto.RentDto;
import com.crud.library.service.BookCopyService;
import com.crud.library.service.ReaderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RentMapperTest {
    @InjectMocks
    private RentMapper rentMapper;
    @Mock
    private BookCopyService bookCopyService;
    @Mock
    private ReaderService readerService;

    @Test
    public void mapToRentDto() {
        //Given
        Reader reader = new Reader("John", "Smith");
        Book book = new Book("title", "author", 1990);
        BookCopy bookCopy = new BookCopy(BookStatus.AVAILABLE, book);
        Rent rent = new Rent(bookCopy, reader);

        //When
        RentDto mappedRentDto = rentMapper.mapToRentDto(rent);
        Date rentDate = rent.getRentDate();

        //Then
        assertEquals(0, mappedRentDto.getBookCopyId());
        assertEquals(rentDate, mappedRentDto.getRentDate());
    }

    @Test
    public void mapToRent() {
        //Given
        Book book = new Book("title", "author", 1999);
        BookCopy bookCopy = new BookCopy(2, BookStatus.UNAVAILABLE, book, new ArrayList<>());
        when(bookCopyService.getBookCopy(2)).thenReturn(bookCopy);

        Reader reader = new Reader(1, "John", "Smith", new Date(), new ArrayList<>());
        when(readerService.getReader(1)).thenReturn(reader);

        RentDto rentDto = new RentDto(1, 2, 1, new Date(), null);

        Date rentDate = rentDto.getRentDate();

        //When
        Rent mappedRent = rentMapper.mapToRent(rentDto);

        //Then
        assertEquals("Smith", mappedRent.getReader().getLastname());
    }

    @Test
    public void mapToRentDtos() {
        //Given
        Reader reader1 = new Reader("John", "Smith");
        Book book1 = new Book("title1", "author1", 1990);
        BookCopy bookCopy1 = new BookCopy(BookStatus.AVAILABLE, book1);
        Rent rent1 = new Rent(bookCopy1, reader1);

        Reader reader2 = new Reader("Michael", "Scott");
        Book book2 = new Book("title2", "author2", 1991);
        BookCopy bookCopy2 = new BookCopy(BookStatus.RENTED, book2);
        Rent rent2 = new Rent(bookCopy2, reader2);

        List<Rent> rents = new ArrayList<>();
        rents.add(rent1);
        rents.add(rent2);

        //When
        List<RentDto> mappedRentDtos = rentMapper.mapToRentDtos(rents);

        //Then
        assertEquals(0, mappedRentDtos.get(1).getReaderId());
        assertNull(mappedRentDtos.get(0).getReturnDate());
    }
}