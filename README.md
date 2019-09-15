Here is a sample application that demonstrates some implementations of GraphQL. It uses an HLSQL jpa repository and a graphql schema loaded from a schema file. 

This example enables Graphiql viewer to expose the graphQL schema. Afte you run the applicaiton, access it as follows:
http://localhost:8091/graphiql
Here you can compose queries e.g.
{
	book(id: "1")
}

The application exposes GraphQL at the following URL
http://localhost:8091/graphql

To use this though, you need to pass the graphQL query as request parameter. Here is a sample query and the equivalent URL to run the following sample GraphQL Query
{
  allBooks {
    isn
    title
    publisher
    publishedDate
  }
}

http://localhost:8092/graphql?query=%7B%0A%20%20allBooks%20%7B%0A%20%20%20%20isn%0A%20%20%20%20title%0A%20%20%20%20publisher%0A%20%20%20%20publishedDate%0A%20%20%7D%0A%7D

To do the URL encodings easily for composing my GraphQL queries for request parameter, I used
https://www.urlencoder.io/

This example also demonstrates how to use a POST Method for GraphQL and defining custom URLs. I guess this is not a great approach for read-only queries.. maybe if there was a use case where GraphQL Mutations was not a good option, then one could use this. I am still learning so more on that later when I do have more knowledge :)

URL: localhost::8091/rest/books
REST METHOD: POST
Sample Read GraphQL Query:
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
