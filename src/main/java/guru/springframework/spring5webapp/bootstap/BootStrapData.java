package guru.springframework.spring5webapp.bootstap;


import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;

import java.util.Arrays;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Manden");
        Author luice = new Author("Luice", "Luck");
        Author adam = new Author("Adam", "Adams");

        Book book1 = new Book("Domain Driven Design 1", "123123123");
        Book book2 = new Book("Domain Driven Design 2", "123123123");
        Book book3 = new Book("Domain Driven Design 3", "123123123");

        authorRepository.saveAll(Arrays.asList(eric, adam, luice));
        bookRepository.saveAll(Arrays.asList(book1, book2, book3));

        eric.getBooks().add(book1);
        luice.getBooks().add(book2);
        adam.getBooks().add(book3);

        authorRepository.saveAll(Arrays.asList(eric, adam, luice));

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());    }
}
