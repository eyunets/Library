package academy.it.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import academy.it.entities.Book;

@Repository
public interface BookDAO extends JpaRepository<Book, Integer> {

	List<Book> findByName(String name);

	Book findByIsbn(String isbn);

	List<Book> findByGenre(String genre);

	Page<Book> findByName(String name, Pageable pageable);

}
