package axp.ea.BookGraphQL.service;


import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import axp.ea.BookGraphQL.model.Book;
import axp.ea.BookGraphQL.repository.BookRepository;
import axp.ea.BookGraphQL.service.datafetcher.AllBooksDataFetcher;
import axp.ea.BookGraphQL.service.datafetcher.BookDataFetcher;

/**
 * @author mjafary
 *
 */
@Service
public class GraphQLService {
	
	@Value("classpath:books.graphql")
	Resource resource;
	private GraphQL graphQL;
	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;
	@Autowired
	private BookRepository bookRepository;
	
	// load schema at application startup
	@PostConstruct
	private void loadSchema() throws IOException{
		
		loadDataIntoHSQL();
		// get the schema
		File schemaFile = resource.getFile();
		// parse the schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private void loadDataIntoHSQL() {
		// TODO Auto-generated method stub
		
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

	private RuntimeWiring buildRuntimeWiring() {
		// TODO Auto-generated method stub
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("allBooks", allBooksDataFetcher)
						.dataFetcher("book", bookDataFetcher))
				.build();
	}
	
	public GraphQL getGraphQL(){
		return graphQL;
	}
}
