package com.rgt.test.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rgt.test.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	Iterable<Book> findByTitleContainingIgnoreCase(String name);

	Page<Book> findAll(Pageable pageable);
	
    List<Book> findAllByActiveTrue();

}
