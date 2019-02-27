package com.crud.library.service;

import com.crud.library.domain.Reader;
import com.crud.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public Reader saveReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader getReader(int id) throws IllegalArgumentException {
        return readerRepository.findOne(id);
    }

    public List<Reader> getReaders() {
        return readerRepository.findAll();
    }
}
