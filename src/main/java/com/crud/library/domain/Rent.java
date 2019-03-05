package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "RENTS")
@NoArgsConstructor
@AllArgsConstructor
public class Rent {
    private int id;
    private BookCopy bookCopy;
    private Reader reader;
    private Date rentDate;
    private Date returnDate;

    public Rent(BookCopy bookCopy, Reader reader) {
        this.bookCopy = bookCopy;
        this.reader = reader;
        this.rentDate = new Date();
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "BOOK_COPY_ID")
    public BookCopy getBookCopy() {
        return bookCopy;
    }

    @ManyToOne
    @JoinColumn(name = "READER_ID")
    public Reader getReader() {
        return reader;
    }

    @Column(name = "RENT_DATE")
    public Date getRentDate() {
        return rentDate;
    }

    @Column(name = "RETURN_DATE")
    public Date getReturnDate() {
        return returnDate;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    private void setReader(Reader reader) {
        this.reader = reader;
    }

    private void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
