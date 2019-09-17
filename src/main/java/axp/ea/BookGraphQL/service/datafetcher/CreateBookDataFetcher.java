package axp.ea.BookGraphQL.service.datafetcher;

import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import axp.ea.BookGraphQL.model.Book;
import axp.ea.BookGraphQL.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author mjafary
 *
 */
@Component
public class CreateBookDataFetcher implements DataFetcher <Book>{
	
	@Autowired
	BookRepository bookRepository;

	@SuppressWarnings("unchecked")
	@Override
	public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
		// TODO Auto-generated method stub
		
		Map<String, Object> dataFetcherMap = dataFetchingEnvironment.getArguments();
		LinkedHashMap<String, Object> fieldMap = new LinkedHashMap<String, Object>();
		fieldMap = (LinkedHashMap<String, Object>) dataFetcherMap.get("input");
				
		String id = fieldMap.get("id").toString();
		String title = fieldMap.get("title").toString();
		String publisher = fieldMap.get("publisher").toString();
		List<String> authorsList = (ArrayList<String>)fieldMap.get("authors");
		String[] authors = new String[authorsList.size()];
		for(int i = 0; i < authorsList.size(); i++){
			authors[i] = authorsList.get(i);
		}
		String publishedDate = fieldMap.get("publishedDate").toString();
		return bookRepository.save(new Book(id, title, publisher, authors, publishedDate));
	}
}