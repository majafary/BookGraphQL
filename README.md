Here is a sample application that demonstrates some implementations of GraphQL. It uses an HLSQL jpa repository and a graphql schema loaded from a schema file.  
  
This example enables Graphiql viewer to expose the graphQL schema. After you run the applicaiton, access it as follows:  
http://localhost:8091/graphiql  
Here you can compose queries e.g.  
  
```
{  
	book(id: "1")  
}  
```
  
The application exposes GraphQL at the following URL  
http://localhost:8091/graphql  
  
To use this though, you need to pass the graphQL query as request parameter. Here is a sample GraphQL query and the equivalent URL to run the query  
  
```
{  
  allBooks {  
    isn  
    title  
    publisher  
    publishedDate  
  }  
}
```
  
http://localhost:8092/graphql?query=%7B%0A%20%20allBooks%20%7B%0A%20%20%20%20isn%0A%20%20%20%20title%0A%20%20%20%20publisher%0A%20%20%20%20publishedDate%0A%20%20%7D%0A%7D  
  
To do the URL encodings easily for composing my GraphQL queries for request parameter, I used  
https://www.urlencoder.io/  
  
This example also demonstrates how to use a POST Method for GraphQL and defining custom URLs. I guess this is not a great approach for read-only queries.. maybe if there was a use case where GraphQL Mutations was not a good option, then one could use this. I am still learning so more on that later when I do have more knowledge :)  
  
URL: localhost::8091/rest/books  
REST METHOD: POST  
Sample Read GraphQL Query: 
  
```
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
```
  
-----------------------------------------------------  
************* Update on Sep 17, 2019 ***************  
Implementated a mutation for CreateBook. Added a mutation type and dataFetcher for this.  
Had to manually convert the map from the datafetching environment to the Book object.  
This may not be the best way to implement. Its another reason why frameworks are there to abstract away all this complexity.  
But for learning, it helps.. Here is the sample mutation query you can run in Graphiql.  

```
mutation {
  createBook(input: {id: "5", title: "Mutation Example", publisher: "MJafary", authors: ["Mansoor", "Jeff", "Shashi"], publishedDate:"today"}) {
    isn
    title
    publisher
    authors
    publishedDate
  }
}
```