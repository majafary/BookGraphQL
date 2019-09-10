package axp.ea.BookGraphQL.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import axp.ea.BookGraphQL.model.Book;

public interface BookRepository extends JpaRepository<Book, String>{

}
