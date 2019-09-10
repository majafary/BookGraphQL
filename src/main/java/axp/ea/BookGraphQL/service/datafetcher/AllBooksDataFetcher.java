package axp.ea.BookGraphQL.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import axp.ea.BookGraphQL.model.Book;
import axp.ea.BookGraphQL.repository.BookRepository;

/**
 * @author mjafary
 *
 */

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>>{
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Book> get(DataFetchingEnvironment arg0) {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

}
