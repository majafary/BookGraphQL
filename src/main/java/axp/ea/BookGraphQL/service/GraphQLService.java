package axp.ea.BookGraphQL.service;


import graphql.GraphQL;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/**
 * @author mjafary
 *
 */
@Service
public class GraphQLService {
	
	private GraphQL graphQL;
		
	@Autowired
	GraphQLSchemaService graphQLSchameBean;

	public GraphQL getGraphQL() throws IOException{
		if(graphQL == null){
			graphQL = GraphQL.newGraphQL(graphQLSchameBean.getSchema()).build();
		}
		return graphQL;
	}
}
