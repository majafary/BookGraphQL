package axp.ea.BookGraphQL.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import axp.ea.BookGraphQL.service.datafetcher.AllBooksDataFetcher;
import axp.ea.BookGraphQL.service.datafetcher.BookDataFetcher;
import axp.ea.BookGraphQL.service.datafetcher.CreateBookDataFetcher;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Component
public class GraphQLSchemaService{

	@Value("classpath:schema.graphqls")
	Resource resource;
	
	@Autowired
	private AllBooksDataFetcher allBooksDataFetcher;
	@Autowired
	private BookDataFetcher bookDataFetcher;
	@Autowired
	private CreateBookDataFetcher createBookDataFetcher;
	
	
	@Bean
	public GraphQLSchema getSchema() throws IOException{
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
				.type("Mutation", typeWiring -> typeWiring.dataFetcher("createBook", createBookDataFetcher))
						.build();
	}

}
