package com.crud.library.service;

import com.crud.library.controller.ActionType;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rent;
import com.crud.library.exception.RentAlreadyReturnedException;
import com.crud.library.exception.RentNotFoundException;
import com.crud.library.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private ReaderService readerService;

    public List<Rent> findRentsOfReader(int readerId) throws EntityNotFoundException{
        Reader reader = Optional.ofNullable(readerService.getReader(readerId))
                .orElseThrow(() -> new EntityNotFoundException("No such reader"));
        return rentRepository.findAllByReader(reader);
    }

    public Rent rentBook(int bookId, int readerId) throws EntityNotFoundException{
        List<BookCopy> availableCopies =
                bookCopyService.getCopiesOfBookWithStatus(bookId, BookStatus.AVAILABLE);
        BookCopy bookCopy = availableCopies.get(0);
        Reader reader = Optional.ofNullable(readerService.getReader(readerId))
                .orElseThrow(() -> new EntityNotFoundException("No such reader"));
        Rent rent = new Rent(bookCopy, reader);
        Rent savedRent = rentRepository.save(rent);

        bookCopy.setStatus(BookStatus.RENTED);
        bookCopyService.saveBookCopy(bookCopy);

        return savedRent;
    }

    public Rent returnBook(int rentId, ActionType actionType) throws RentNotFoundException, RentAlreadyReturnedException {
        Rent rent = Optional.ofNullable(rentRepository.findOne(rentId))
                .orElseThrow(RentNotFoundException::new);

        if (rent.getReturnDate() != null) {
            throw new RentAlreadyReturnedException();
        }

        BookCopy bookCopy = rent.getBookCopy();
        if (actionType == ActionType.RETURN) {
            bookCopy.setStatus(BookStatus.AVAILABLE);
        } else {
            bookCopy.setStatus(BookStatus.UNAVAILABLE);
        }
        bookCopyService.saveBookCopy(bookCopy);

        rent.setReturnDate(new Date());
        Rent savedRent = rentRepository.save(rent);

        return savedRent;
    }

    public Rent getRent(int id) {
        return rentRepository.findOne(id);
    }

    public List<Rent> getRents() {
        return rentRepository.findAll();
    }
}
