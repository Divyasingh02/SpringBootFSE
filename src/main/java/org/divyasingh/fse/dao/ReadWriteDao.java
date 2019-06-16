package org.divyasingh.fse.dao;

import java.util.List;

import org.divyasingh.fse.entity.Book;
import org.divyasingh.fse.entity.Subject;

public interface ReadWriteDao {
    List<Book> findBooksByTitle(String bookTitle);

    List<Subject> findSubjectsByTitle(String subjectTitle);

    Subject findSubjectById(Long subjectId);

    Book findBookById(Long bookId);

    void saveSubject(Subject subject);

    void saveBook(Book book, Subject subject);

    void removeBook(String bookId);

    void removeSubject(String subjectID);
    
    List<Book> findAllBooks();
    List<Subject> findAllSubjects();
}
