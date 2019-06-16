package org.divyasingh.fse.dao;


import org.divyasingh.fse.entity.Book;
import org.divyasingh.fse.entity.Subject;
import org.divyasingh.fse.repository.BookRepository;
import org.divyasingh.fse.repository.SubjectRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ReadWriteDaoImpl implements ReadWriteDao {

	private BookRepository bookRepository;
	
	private SubjectRepository subjectRepository;
    @Autowired
    public ReadWriteDaoImpl(BookRepository bookRepository, SubjectRepository subjectRepository) {
        this.bookRepository = bookRepository;
        this.subjectRepository = subjectRepository;
    }

    public List<Book> findAllBooks() {

        List<Book> books = null;

        try {

           //books = bookRepository.findBooksByTitle(bookTitle);
        	books = bookRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (books == null) {
            books = new ArrayList<>();
        }
        return books;
    }

    public List<Subject> findAllSubjects() {

        List<Subject> subjects = null;

        try {
            //subjects = subjectRepository.findSubjectsByTitle(subjectTitle);
        	subjects = subjectRepository.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (subjects == null) {
            subjects = new ArrayList<>();
        }

        return subjects;
    }

    @Override
    public Subject findSubjectById(Long subjectId) {

        Subject subject = null;

        try {

            subject = subjectRepository.findById(subjectId);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return subject;
    }

    @Override
    public Book findBookById(Long bookId) {
        Book book = null;

        try {
            book = bookRepository.findById(bookId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return book;
    }

    public void saveSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public void saveBook(Book book, Subject subject) {

        book.setSubject(subject);
        bookRepository.save(book);

    }

    public void removeBook(String bookId) {
        bookRepository.delete(new Book(Long.valueOf(bookId)));
    }

    public void removeSubject(String subjectID) {
        subjectRepository.delete(new Subject(Long.valueOf(subjectID)));
    }

	@Override
	public List<Book> findBooksByTitle(String bookTitle) {
		List<Book> books = null;

        try {
            books = bookRepository.findByTitleIgnoreCaseContaining(bookTitle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (books == null) {
        	books = new ArrayList<>();
        }

        return books;
	}

	@Override
	public List<Subject> findSubjectsByTitle(String subjectTitle) {
		List<Subject> subjects = null;

        try {
            subjects = subjectRepository.findBySubtitleIgnoreCaseContaining(subjectTitle);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (subjects == null) {
            subjects = new ArrayList<>();
        }

        return subjects;
	}
}
