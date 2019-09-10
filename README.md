A simple GraphQL Example using Spring Boot. This is a REST application

URL: localhost::8091/graph/books
REST METHOD: POST
Sample GraphQL Query:
{
	allBooks{
		title
		authors
	}
	book(id: "1"){
		title
		publisher
		authors
	}
}
