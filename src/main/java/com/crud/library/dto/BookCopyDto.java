package com.crud.library.dto;

import com.crud.library.domain.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopyDto {
    private int id;
    private BookStatus status;
    private int bookId;
}
