package com.crud.library.mapper;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rent;
import com.crud.library.dto.RentDto;
import com.crud.library.service.BookCopyService;
import com.crud.library.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class RentMapper {
    @Autowired
    private BookCopyService bookCopyService;
    @Autowired
    private ReaderService readerService;

    public RentDto mapToRentDto(Rent rent) {
        return new RentDto(
                rent.getId(),
                rent.getBookCopy().getId(),
                rent.getReader().getId(),
                rent.getRentDate(),
                rent.getReturnDate());
    }

    public Rent mapToRent(RentDto rentDto) throws EntityNotFoundException{
        BookCopy bookCopy = Optional.ofNullable(
                bookCopyService.getBookCopy(rentDto.getBookCopyId()))
                .orElseThrow(() -> new EntityNotFoundException("Book copy with this ID not found"));
        Reader reader = Optional.ofNullable(
                readerService.getReader(rentDto.getReaderId()))
                .orElseThrow(() -> new EntityNotFoundException("Reader with this ID not found"));

        return new Rent(
                rentDto.getId(),
                bookCopy,
                reader,
                new Date(),
                rentDto.getReturnDate());
    }

    public List<RentDto> mapToRentDtos(List<Rent> rents) {
        return rents.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }
}
