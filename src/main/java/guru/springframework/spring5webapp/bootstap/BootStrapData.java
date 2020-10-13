package guru.springframework.spring5webapp.bootstap;


import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(
            AuthorRepository authorRepository,
            BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher();
        publisher.setName("london");
        publisher.setAddressLine1("Sweets street");
        publisher.setCity("london");
        publisher.setState("colorado");
        publisher.setZip("71511");

        publisherRepository.save(publisher);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of publishers available: " + publisherRepository.count());

        Author eric = new Author("Eric", "Manden");
        Author luice = new Author("Luice", "Luck");
        Author adam = new Author("Adam", "Adams");

        Book book1 = new Book("Domain Driven Design 1", "123123123");
        book1.setPublisher(publisher);
        Book book2 = new Book("Domain Driven Design 2", "123123123");
        book2.setPublisher(publisher);
        Book book3 = new Book("Domain Driven Design 3", "123123123");
        book3.setPublisher(publisher);

        publisher.getBooks().add(book1);
        publisher.getBooks().add(book2);
        publisher.getBooks().add(book3);

        eric.getBooks().add(book1);
        luice.getBooks().add(book2);
        adam.getBooks().add(book3);

        authorRepository.saveAll(Arrays.asList(eric, adam, luice));
        bookRepository.saveAll(Arrays.asList(book1, book2, book3));
        publisherRepository.save(publisher);

        System.out.println("Number of Books: " + bookRepository.count());

    }
}
