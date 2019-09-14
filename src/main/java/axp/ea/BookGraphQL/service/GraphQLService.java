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
	
	@Value("classpath:schema.graphqls")
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
		// get the schema
		File schemaFile = resource.getFile();
		// parse the schema
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
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
