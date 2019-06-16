package org.divyasingh.fse.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.divyasingh.fse.dao.ReadWriteDao;
import org.divyasingh.fse.entity.Book;
import org.divyasingh.fse.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadAndWriteOperationImpl implements ReadAndWriteOperation{

	private ReadWriteDao readWriteDao;

    @Autowired
    public ReadAndWriteOperationImpl(ReadWriteDao readWriteDao) {
        this.readWriteDao = readWriteDao;
    }

    public void addSubject(Subject subject) {
    	try {
           readWriteDao.saveSubject(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book, int subjectId) {

        try {
            readWriteDao.saveBook(book, new Subject(subjectId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(String bookId) {
    	List<Book> books = null;
        try {
         readWriteDao.removeBook(bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void deleteSubject(String subjectId) {

    	try{
    		readWriteDao.removeSubject(subjectId);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public List<Book> searchBooks(String bookTitle) {
        List<Book> books = readWriteDao.findBooksByTitle(bookTitle);

        if (books == null) {
            books = new ArrayList<>();
        }

        return books;
    }

    public List<Subject> searchSubjects(String subjectTitle) {
        List<Subject> resultSubjectList = readWriteDao.findSubjectsByTitle(subjectTitle);
        if(resultSubjectList == null) {
        	resultSubjectList = new ArrayList<>();
        }
        return resultSubjectList;
    }
    
    public List<Book> findAllBooks() {
    	List<Book> books = readWriteDao.findAllBooks();

        if (books == null) {
            books = new ArrayList<>();
        }

        return books;
    }
    
    public List<Subject> findAllSubjects() {
    	List<Subject> resultSubjectList = readWriteDao.findAllSubjects();
        if(resultSubjectList == null) {
        	resultSubjectList = new ArrayList<>();
        }
        return resultSubjectList;
    }

}
