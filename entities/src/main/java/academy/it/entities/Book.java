package academy.it.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOOK_ID")
	@JsonIgnore
	private Integer bookID;
	@Column(name = "NAME")
	private String name;
	@Column(name = "ISBN")
	private String isbn;
	@Column(name = "GENRE")
	private String genre;
	@Column(name = "YEAR")
	private Integer year;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "BOOK_AUTHOR", joinColumns = { @JoinColumn(name = "BOOK_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "AUTHOR_ID") })
	@JsonManagedReference
	private Set<Author> authors;

	@OneToMany(mappedBy = "book", orphanRemoval = true)
	@JsonIgnore
	private Set<Form> forms;

	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", name=" + name + ", isbn=" + isbn + ", genre=" + genre + ", year=" + year
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookID == null) {
			if (other.bookID != null)
				return false;
		} else if (!bookID.equals(other.bookID))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookID == null) ? 0 : bookID.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}
}
