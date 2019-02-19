package com.crud.library.repository;

import com.crud.library.domain.BookCopy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookCopyRepository extends CrudRepository<BookCopy, Integer> {
}
