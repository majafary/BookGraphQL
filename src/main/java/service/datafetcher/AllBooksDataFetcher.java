/**
 * 
 */
package service.datafetcher;

import java.util.List;

import model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

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
