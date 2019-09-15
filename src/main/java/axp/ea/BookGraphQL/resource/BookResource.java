package axp.ea.BookGraphQL.resource;

import java.io.IOException;

import graphql.ExecutionResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import axp.ea.BookGraphQL.service.GraphQLService;


/**
 * @author mjafary
 */

@RestController
public class BookResource {
	
	@Autowired
	GraphQLService graphQLService;
	
	@RequestMapping(method=RequestMethod.POST, value="/rest/books")
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) throws IOException{
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
	
	@RequestMapping("/health")
	public String getHealth(){
			return "OK";
	}
}
