package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import com.crud.library.domain.Reader;
import com.crud.library.dto.BookCopyDto;
import com.crud.library.dto.BookDto;
import com.crud.library.dto.ReaderDto;
import com.crud.library.dto.RentDto;
import com.crud.library.mapper.BookCopyMapper;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.mapper.RentMapper;
import com.crud.library.service.BookCopyService;
import com.crud.library.service.BookService;
import com.crud.library.service.ReaderService;
import com.crud.library.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class LibraryController {
    @Autowired
    private ReaderService readerService;
    @Autowired
    private ReaderMapper readerMapper;
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private BookCopyMapper bookCopyMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RentService rentService;
    @Autowired
    private RentMapper rentMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/readers", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ReaderDto createReader(@RequestBody ReaderDto readerDto) {
        return readerMapper.mapToReaderDto(readerService.saveReader(readerMapper.mapToReader(readerDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readers")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtos(readerService.getReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/readers/{id}")
    public ReaderDto getReader(@PathVariable int id) throws EntityNotFoundException {
        Reader reader = Optional.ofNullable(readerService.getReader(id)).
                orElseThrow(() -> new EntityNotFoundException("Reader not found"));
        return readerMapper.mapToReaderDto(reader);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookMapper.mapToBookDto(bookService.saveBookCopy(bookMapper.mapToBook(bookDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtos(bookService.getBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
    public BookDto getBook(@PathVariable int id) {
        Book book = Optional.ofNullable(bookService.getBook(id))
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return bookMapper.mapToBookDto(book);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/books/copies", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public BookCopyDto createBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        return bookCopyMapper.mapToBookCopyDto(bookCopyService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/copies")
    public List<BookCopyDto> getBookCopies() {
        return bookCopyMapper.mapToBookCopyDtos(bookCopyService.getBookCopies());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/copies/{id}")
    public BookCopyDto getBookCopy(@PathVariable int id) {
        BookCopy bookCopy = Optional.ofNullable(bookCopyService.getBookCopy(id))
                .orElseThrow(() -> new EntityNotFoundException("Book copy not found"));
        return bookCopyMapper.mapToBookCopyDto(bookCopy);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/books/copies", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public BookCopyDto updateBookCopy(@RequestBody BookCopyDto bookCopyDto) {
        return bookCopyMapper.mapToBookCopyDto(bookCopyService.saveBookCopy(bookCopyMapper.mapToBookCopy(bookCopyDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/books/{bookId}/copies")
    public int checkNumberOfCopiesWithStatus(@RequestParam BookStatus status, @PathVariable int bookId) {
        return bookCopyService.checkNumberOfCopiesWithStatus(status, bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rents")
    public RentDto rentBook(@RequestParam int readerId, @RequestParam int bookId) {
        return rentMapper.mapToRentDto(rentService.rentBook(bookId, readerId));
    }
}
