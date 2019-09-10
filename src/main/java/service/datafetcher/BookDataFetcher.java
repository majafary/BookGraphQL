/**
 * 
 */
package service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import repository.BookRepository;
import model.Book;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author mjafary
 *
 */
@Component
public class BookDataFetcher implements DataFetcher<Book>{
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
		// TODO Auto-generated method stub
		String isn = dataFetchingEnvironment.getArgument("id");
		return bookRepository.findById(isn).get();
	}

}
