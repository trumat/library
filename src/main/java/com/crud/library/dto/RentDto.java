package com.crud.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {
    private int id;
    private int bookCopyId;
    private int readerId;
    private Date rentDate;
    private Date returnDate;
}
