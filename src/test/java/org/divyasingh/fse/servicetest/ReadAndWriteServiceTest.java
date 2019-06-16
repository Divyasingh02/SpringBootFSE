package org.divyasingh.fse.servicetest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.divyasingh.fse.dao.ReadWriteDaoImpl;
import org.divyasingh.fse.entity.Book;
import org.divyasingh.fse.entity.Subject;
import org.divyasingh.fse.service.ReadAndWriteOperationImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ReadAndWriteServiceTest {

	@InjectMocks
	ReadAndWriteOperationImpl readAndWriteOperation;
	
	@Mock
	ReadWriteDaoImpl readWriteDao;
	
	Book book;
	Subject subject;
	@Before()
	public void init(){
		subject = new Subject("Geo", 2);
		LocalDate localDate= LocalDate.now();
		book = new Book("History of Harappa", 450.50, 2,localDate);
	}
	
	@Test
	public void testAddSubject(){
		readAndWriteOperation.addSubject(subject);
	}
	
	@Test
	public void testAddBook() {
		readAndWriteOperation.addBook(book, 2);
	}
	
	@Test
	public void testDeleteBook(){
		readAndWriteOperation.deleteBook("History of Harappa");
	}
	
	@Test
	public void testDeleteSubject(){
		readAndWriteOperation.deleteSubject("5");
	}
	
	@Test
	public void testSearchSubjects(){
		List<Subject> mocklist = new ArrayList<>();
		mocklist.add(subject);
		Mockito.when(readAndWriteOperation.searchSubjects("Geo")).thenReturn(mocklist);
		List<Subject> subjects = readAndWriteOperation.searchSubjects("Geo");
		Assert.assertEquals("Geo", subjects.get(0).getSubtitle());
		Assert.assertEquals(2, subjects.get(0).getDurationInHours());
	}
	
	@Test
	public void testSearchBooks() {
		List<Book> mocklist = new ArrayList<>();
		mocklist.add(book);
		Mockito.when(readAndWriteOperation.searchBooks("History of Harappa")).thenReturn(mocklist);
		List<Book> books = readAndWriteOperation.searchBooks("History of Harappa");
		Assert.assertEquals("History of Harappa", books.get(0).getTitle());
		Assert.assertEquals(450.50, books.get(0).getPrice());
		Assert.assertEquals(Integer.valueOf(2), books.get(0).getVolume());
	}
	
	@Test
	public void testFindAllBooks(){
		List<Book> mocklist = new ArrayList<>();
		mocklist.add(book);
		LocalDate localDate= LocalDate.now();
		Book book1 = new Book("Computers Application", 500.50, 1,localDate);
		mocklist.add(book1);
		Mockito.when(readAndWriteOperation.findAllBooks()).thenReturn(mocklist);
		List<Book> books = readAndWriteOperation.findAllBooks();
		Assert.assertEquals(2, books.size());
		Assert.assertEquals(500.50, books.get(1).getPrice());	
	}
	
	@Test
	public void testFindAllSubjects(){
		List<Subject> mocklist = new ArrayList<>();
		mocklist.add(subject);
		Subject subject1 = new Subject("Philosophy", 1);
		mocklist.add(subject1);
		Mockito.when(readAndWriteOperation.findAllSubjects()).thenReturn(mocklist);
		
		List<Subject> subjects = readAndWriteOperation.findAllSubjects();
		Assert.assertEquals(2, subjects.size());
		Assert.assertEquals(1, subjects.get(1).getDurationInHours());
	}
}
