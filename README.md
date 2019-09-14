GraphQL samples I have been playing with.

Uses a jpa repository and a graphql schema loaded from a file
experimenting with the graphiQL exposure and trying to find the middle ground between the two implementations.. 
implements

Graphiql: localhost::8091/graphiql

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
