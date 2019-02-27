package com.crud.library.service;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rent;
import com.crud.library.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private ReaderService readerService;

    public Rent rentBook(int bookId, int readerId) {
        List<BookCopy> availableCopies =
                bookCopyService.getCopiesOfBookWithStatus(bookId, BookStatus.AVAILABLE);
        BookCopy bookCopy = availableCopies.get(0);
        Reader reader = readerService.getReader(readerId);
        Rent rent = new Rent(bookCopy, reader);
        Rent savedRent = rentRepository.save(rent);

        bookCopy.setStatus(BookStatus.RENTED);
        bookCopyService.saveBookCopy(bookCopy);

        return savedRent;
    }

    public Rent getRent(int id) {
        return rentRepository.findOne(id);
    }

    public List<Rent> getRents() {
        return rentRepository.findAll();
    }
}
