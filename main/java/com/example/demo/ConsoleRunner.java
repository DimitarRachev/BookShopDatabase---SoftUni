package com.example.demo;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BooksRepository;
import com.example.demo.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private SeedService seedService;
    private final BooksRepository booksRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService, BooksRepository booksRepository, AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.booksRepository = booksRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Polulate repository
//        this.seedService.seedAuthors();
//        this.seedService.seedCategories();
//        this.seedService.seedBooks();
        //search queries
//        this._01booksAfter2000();
//        this._02AuthorsWithBooksBefore1990();
//        this._03AllAuthorsOrderedByBookCount();
        this._04GetAllBooksFromGeorgeAndSort();
    }

    private void _04GetAllBooksFromGeorgeAndSort() {
        List<Book> books = this.booksRepository.findAllByAuthor_FirstNameAndAuthorLastName("George", "Powell");
        books.forEach(b -> System.out.println(b.getTitle()));
    }

    private void _03AllAuthorsOrderedByBookCount() {
        List<Author> all = this.authorRepository.findAll();
        all.stream()
                .sorted((a1, a2) -> a2.getBooks().size() - a1.getBooks().size())
                .forEach(e -> System.out.printf("%s %s -> %d%n", e.getFirstName(), e.getLastName(), e.getBooks().size()));
    }

    private void _02AuthorsWithBooksBefore1990() {
        LocalDate year1990 = LocalDate.of(1990, 1, 1);
        List<Author> authors = this.authorRepository.findDistinctByBooksReleaseDateBefore(year1990);
        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
    }

    private void _01booksAfter2000() {
        LocalDate year2000 = LocalDate.of(2000, 1, 1);
        List<Book> books = booksRepository.findByReleaseDateAfter(year2000);
        books.forEach(b -> System.out.println(b.getTitle()));

        int count = this.booksRepository.countByReleaseDateAfter(year2000);
        System.out.println("Total count: " + count);
    }
}
