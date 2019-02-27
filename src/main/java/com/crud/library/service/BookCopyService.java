package com.crud.library.service;


import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.repository.BookCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookCopyService {
    @Autowired
    private BookCopyRepository bookCopyRepository;

    public BookCopy saveBookCopy(BookCopy bookCopy) {
        return bookCopyRepository.save(bookCopy);
    }

    public List<BookCopy> getBookCopies() {
        return bookCopyRepository.findAll();
    }

    public BookCopy getBookCopy(int id) {
        return bookCopyRepository.findOne(id);
    }

    public int checkNumberOfCopiesWithStatus(BookStatus status, int bookId) {
        return bookCopyRepository.countBookCopiesByBookIdAndStatus(bookId, status);
    }

    public List<BookCopy> getCopiesOfBookWithStatus(int bookId, BookStatus status) {
        return bookCopyRepository.findAllByBookIdAndStatus(bookId, status);
    }
}
