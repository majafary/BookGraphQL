package axp.ea.BookGraphQL;

import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import axp.ea.BookGraphQL.model.Book;
import axp.ea.BookGraphQL.repository.BookRepository;

@SpringBootApplication
public class GraphQlTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphQlTestApplication.class, args);
	}
	
	   @Autowired
	    private BookRepository bookRepository;
		// load data at application startup
		@PostConstruct
		private void loadDataIntoHSQL() {
			// load books into books repository. this saves the books in HSQL
			Stream.of(
					new Book("1","Book1","Kindle", new String[] {"Author1"}, "Nov 2014"),
					new Book("2","Book2","Kindle", new String[] {"Author2"}, "Nov 2015"),
					new Book("3","Book3","Kindle", new String[] {"Author3"}, "Nov 2016"),
					new Book("4","Book4","Kindle", new String[] {"Author4"}, "Nov 2017"))
					.forEach(book -> {
						bookRepository.save(book);
					});
		}
}
