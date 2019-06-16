package org.divyasingh.fse.service;

import java.util.List;
import java.util.Set;

import org.divyasingh.fse.entity.Book;
import org.divyasingh.fse.entity.Subject;

public interface ReadAndWriteOperation {
    void addSubject(Subject subject);

    void addBook(Book book, int subjectId);

    void deleteSubject(String subjectId);

    void deleteBook(String bookId);

    List<Book> searchBooks(String bookTitle);

    List<Subject> searchSubjects(String SubjectTitle);
    
    List<Book> findAllBooks();
    List<Subject> findAllSubjects();
}
