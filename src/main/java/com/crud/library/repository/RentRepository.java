package com.crud.library.repository;

import com.crud.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RentRepository extends CrudRepository<Rent, Integer> {
}
