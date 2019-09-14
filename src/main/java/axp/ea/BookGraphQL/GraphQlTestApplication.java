package axp.ea.BookGraphQL;

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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

import axp.ea.BookGraphQL.model.Book;
import axp.ea.BookGraphQL.repository.BookRepository;
import axp.ea.BookGraphQL.service.datafetcher.AllBooksDataFetcher;
import axp.ea.BookGraphQL.service.datafetcher.BookDataFetcher;

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

		@Value("classpath:schema.graphqls")
		Resource resource;
		
		@Autowired
		private  AllBooksDataFetcher allBooksDataFetcher;
		@Autowired
		private  BookDataFetcher bookDataFetcher;
		
		@Bean
	    GraphQLSchema schema() throws IOException{
			// get the schema
			File schemaFile = resource.getFile();
			// parse the schema
			TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
			RuntimeWiring wiring = buildRuntimeWiring();
			return new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
	    }
		private  RuntimeWiring buildRuntimeWiring() {
			// TODO Auto-generated method stub
			return RuntimeWiring.newRuntimeWiring()
					.type("Query", typeWiring -> typeWiring
							.dataFetcher("allBooks", allBooksDataFetcher)
							.dataFetcher("book", bookDataFetcher))
							.build();
		}

}
