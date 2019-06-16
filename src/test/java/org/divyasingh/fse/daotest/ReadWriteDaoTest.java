package org.divyasingh.fse.daotest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.divyasingh.fse.dao.ReadWriteDao;
import org.divyasingh.fse.entity.Book;
import org.divyasingh.fse.entity.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ReadWriteDaoTest {

	@Autowired
	ReadWriteDao readWriteDao;
	
	Book book;
	Subject subject;
	
	@Before
	public void init(){
		subject = new Subject("Philosophy", 2);
		LocalDate localDate= LocalDate.now();
		book = new Book("History of Harappa", 450.50, 2,localDate);
	}
	
	@Test
	public void testFindAllBooks() {
		List<Book> list = readWriteDao.findAllBooks();
		System.out.println(list);
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testFindAllSubjects(){
		List<Subject> list = readWriteDao.findAllSubjects();
		System.out.println(list);
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testFindSubjectById() {
		Subject subject = readWriteDao.findSubjectById(3L);
		Assert.assertNotNull(subject);
	}
	
	@Test
	public void testFindBookById() {
		Book book = readWriteDao.findBookById(5L);
		Assert.assertNotNull(book);
	}
	
	@Test
	public void testFindSubjectsByTitle() {
		List<Subject> subjects = readWriteDao.findSubjectsByTitle("Psychology");
		System.out.println(subjects);
		Assert.assertNotNull(subjects);
	}
	
	@Test
	public void testFindBooksByTitle() {
		List<Book> books = readWriteDao.findBooksByTitle("Organic Chemistry");
		System.out.println(books);
		Assert.assertNotNull(books);
	}
	
	@Test
	public void testSaveSubject() {
		readWriteDao.saveSubject(subject);
		List<Subject> subjects = readWriteDao.findSubjectsByTitle(subject.getSubtitle());
		Assert.assertEquals("Philosophy", subjects.get(0).getSubtitle());
	}
	
	@Test
	public void testSaveBook() {
		readWriteDao.saveBook(book, new Subject(3L));
		List<Book> books = readWriteDao.findBooksByTitle(book.getTitle());
		System.out.println(books);
		Assert.assertEquals("History of Harappa", books.get(0).getTitle());
	}
	
	@Test
	public void testRemoveBook() {
		readWriteDao.removeBook("7");
		Book booksearch = readWriteDao.findBookById(7L);
		Assert.assertNull(booksearch);
	}
	
	@Test
	public void testRemoveSubject() {
		readWriteDao.removeSubject("3");
		Subject subjectsearch = readWriteDao.findSubjectById(3L);
		Assert.assertNull(subjectsearch);
	}
	
}
