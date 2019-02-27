package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import com.crud.library.domain.BookStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {
    @Override
    List<BookCopy> findAll();

    int countBookCopiesByBookIdAndStatus(int bookId, BookStatus status);

    List<BookCopy> findAllByBookIdAndStatus(int bookId, BookStatus bookStatus);
}
