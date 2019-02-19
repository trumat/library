package com.crud.library.repository;

import com.crud.library.domain.Reader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderRepositoryTest {
    @Autowired
    private ReaderRepository readerRepository;

    @Test
    public void shouldSaveReader() {
        //Given
        Reader reader = new Reader("John", "Smith");
        //When
        readerRepository.save(reader);
        Reader retrievedReader = readerRepository.findOne(1);
        //Then
        assertEquals("John", retrievedReader.getFirstname());
    }
}