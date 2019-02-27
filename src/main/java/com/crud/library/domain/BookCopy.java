package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BOOK_COPIES")
@NoArgsConstructor
@AllArgsConstructor
public class BookCopy {
    private int id;
    private BookStatus status;
    private Book book;
    private List<Rent> rents = new ArrayList<>();

    public BookCopy(BookStatus status, Book book) {
        this.status = status;
        this.book = book;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    public int getId() {
        return id;
    }

    @Column(name = "STATUS")
    public BookStatus getStatus() {
        return status;
    }

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    public Book getBook() {
        return book;
    }

    @OneToMany(targetEntity = Rent.class, mappedBy = "bookCopy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Rent> getRents() {
        return rents;
    }

    private void setId(int id) {
        this.id = id;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    private void setBook(Book book) {
        this.book = book;
    }

    private void setRents(List<Rent> rents) { this.rents = rents;}
}
