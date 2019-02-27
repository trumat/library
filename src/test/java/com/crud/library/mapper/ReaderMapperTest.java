package com.crud.library.mapper;

import com.crud.library.domain.*;
import com.crud.library.dto.ReaderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderMapperTest {
    @Autowired
    private ReaderMapper readerMapper;

    @Test
    public void mapToReaderDto() {
        //Given
        Reader reader = new Reader("John", "Smith");
        Book book = new Book("title", "author", 1990);
        BookCopy bookCopy = new BookCopy(BookStatus.AVAILABLE, book);
        Rent rent = new Rent(bookCopy, reader);
        reader.getRents().add(rent);

        //When
        ReaderDto mappedReaderDto = readerMapper.mapToReaderDto(reader);

        //Then
        assertEquals("John", mappedReaderDto.getFirstname());
    }

    @Test
    public void mapToReader() {
        //Given
        ReaderDto readerDto = new ReaderDto(1, "John", "Smith", new Date());

        //When
        Reader mappedReader = readerMapper.mapToReader(readerDto);

        //Then
        assertEquals("Smith", mappedReader.getLastname());
    }

    @Test
    public void mapToReaderDtos() {
        //Given
        Reader reader1 = new Reader("John", "Smith");
        Book book1 = new Book("title1", "author1", 1990);
        BookCopy bookCopy1 = new BookCopy(BookStatus.AVAILABLE, book1);
        Rent rent1 = new Rent(bookCopy1, reader1);
        reader1.getRents().add(rent1);

        Reader reader2 = new Reader("Michael", "Scott");
        Book book2 = new Book("title2", "author2", 1991);
        BookCopy bookCopy2 = new BookCopy(BookStatus.RENTED, book2);
        Rent rent2 = new Rent(bookCopy2, reader2);
        reader2.getRents().add(rent2);

        List<Reader> readers = new ArrayList<>();
        readers.add(reader1);
        readers.add(reader2);

        //When
        List<ReaderDto> mappedReaderDtos = readerMapper.mapToReaderDtos(readers);

        //Then
        assertEquals(reader2.getAccountCreated(), mappedReaderDtos.get(1).getAccountCreated());
        assertEquals("Smith", mappedReaderDtos.get(0).getLastname());
    }

    @Test
    public void mapToReaders() {
        //Given
        ReaderDto readerDto1 = new ReaderDto(1, "John", "Smith", new Date());
        ReaderDto readerDto2 = new ReaderDto(1, "Michael", "Scott", new Date());

        List<ReaderDto> readerDtos = new ArrayList<>();
        readerDtos.add(readerDto1);
        readerDtos.add(readerDto2);

        //When
        List<Reader> mappedReaders = readerMapper.mapToReaders(readerDtos);

        //Then
        assertEquals("Smith", mappedReaders.get(0).getLastname());
        assertEquals(new ArrayList<>(), mappedReaders.get(1).getRents());
    }
}