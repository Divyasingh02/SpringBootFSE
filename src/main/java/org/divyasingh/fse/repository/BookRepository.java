package org.divyasingh.fse.repository;

import java.util.List;

import org.divyasingh.fse.entity.Book;
import org.divyasingh.fse.entity.Subject;
import org.springframework.data.repository.Repository;

public interface BookRepository extends Repository<Book, Long> {
	List<Book> findByTitleIgnoreCaseContaining(String title);
	List<Book> findAll();
	Book findById(Long bookId);
	Book save(Book book);
	void delete(Book book);
}
