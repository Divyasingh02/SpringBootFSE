package org.divyasingh.fse.controller;

import org.divyasingh.fse.entity.Book;
import org.divyasingh.fse.entity.Subject;
import org.divyasingh.fse.model.*;
import org.divyasingh.fse.service.ReadAndWriteOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AppController {

    @Autowired
    public ReadAndWriteOperation readWriteOperation;
    
    private List<Subject> subjects;
    List<Book> books;

    private void initData(){
        books = readWriteOperation.findAllBooks();
        subjects = readWriteOperation.findAllSubjects();
    }

    @GetMapping("/")
    public String MainForm(Locale locale, Model model) {
    	initData();

        Map<Long, String> subjectMap = subjects.stream().collect(Collectors.toMap(Subject::getSubjectId,Subject::getSubtitle));
        model.addAttribute("subjects", subjects);
        model.addAttribute("books", books);
        model.addAttribute("subjectMap", subjectMap);

        return "subject";
    }

    @GetMapping("/subjectsearch")
    public String searchForm() {
        return "subjectsearch";
    }
    
    @GetMapping("/booksearch")
    public String searchBookForm() {
        return "booksearch";
    }

    @GetMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") String bookId) {

        System.out.println("BookId:"+bookId);
        readWriteOperation.deleteBook(bookId);
        return "redirect:/";
    }

    @GetMapping("/deleteSubject/{subjectId}")
    public String deleteSubject(@PathVariable("subjectId") String subjectId) {

        System.out.println("SubjectId:"+subjectId);
        readWriteOperation.deleteSubject(subjectId);
        return "redirect:/";
    }

    @PostMapping("addSubject")
    public String saveSubject(@ModelAttribute("subject") @Valid Subject subject, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("subjects", readWriteOperation.searchSubjects(""));
            model.addAttribute("books", readWriteOperation.searchBooks(""));
            return "subject";
        }

        readWriteOperation.addSubject(subject);

        return "redirect:/";
    }

    @PostMapping("addBook")
    public String saveBook(@ModelAttribute("bookDto") @Valid BookModel bookDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("subjects", readWriteOperation.searchSubjects(""));
            model.addAttribute("books", readWriteOperation.searchBooks(""));
            return "subject";
        }

        readWriteOperation.addBook(new Book(bookDto.getBookId(), bookDto.getTitle(), bookDto.getPrice(), bookDto.getVolume(), bookDto.getPublishDate()),bookDto.getSubjectId());

        return "redirect:/";
    }

    @PostMapping("searchSubject")
    public String searchSubject(@ModelAttribute("searchSubject") @Valid SearchSubject searchSubject, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("searchSubject", searchSubject);
            return "subjectsearch";
        }

        model.addAttribute("subjects", readWriteOperation.searchSubjects(searchSubject.getSubtitle()));

        return "subjectsearch";
    }

    @PostMapping("searchBook")
    public String searchBook(@ModelAttribute("searchBook") @Valid SearchBook searchBook, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("searchBook", searchBook);
            return "booksearch";
        }

        model.addAttribute("books", readWriteOperation.searchBooks(searchBook.getTitle()));

        return "booksearch";
    }

    @ModelAttribute("subject")
    public Subject formBackingSubject() {
        return new Subject();
    }

    @ModelAttribute("searchSubject")
    public SearchSubject formForSearchSubject() {
        return new SearchSubject();
    }

    @ModelAttribute("bookDto")
    public BookModel formBackingBook() {
        return new BookModel();
    }


    @ModelAttribute("searchBook")
    public SearchBook formForSearchBook() {
        return new SearchBook();
    }
}
