package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookCopy;
import com.crud.library.dto.BookCopyDto;
import com.crud.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookCopyMapper {
    @Autowired
    private BookService bookService;

    public BookCopyDto mapToBookCopyDto(BookCopy bookCopy) {
        return new BookCopyDto(
                bookCopy.getId(),
                bookCopy.getStatus(),
                bookCopy.getId());
    }

    public BookCopy mapToBookCopy(BookCopyDto bookCopyDto) throws EntityNotFoundException{
        Book book = Optional.of(
                bookService.getBook(bookCopyDto.getBookId()))
                .orElseThrow(() -> new EntityNotFoundException("Book with this ID not found"));

        return new BookCopy(
                bookCopyDto.getId(),
                bookCopyDto.getStatus(),
                book,
                new ArrayList<>());
    }

    public List<BookCopyDto> mapToBookCopyDtos(List<BookCopy> bookCopies) {
        return bookCopies.stream()
                .map(this::mapToBookCopyDto)
                .collect(Collectors.toList());
    }
}
