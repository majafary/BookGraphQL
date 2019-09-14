package axp.ea.BookGraphQL.resource;

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
//   {
//	allBooks{
//		title
//		authors
//	}
//	book(id: "1"){
//		title
//		publisher
//		authors
//	}
}
 */

@RestController
public class BookResource {
	
	@Autowired
	GraphQLService graphQLService;
	
//	@PostMapping
	@RequestMapping(method=RequestMethod.POST, value="/rest/books")
	public ResponseEntity<Object> getAllBooks(@RequestBody String query){
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
	@RequestMapping("/health")
//	public ResponseEntity<Object> getHealth(){
	public String getHealth(){

			return "OK";
	}
}
