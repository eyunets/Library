package academy.it.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FORM")
public class Form {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FORM_ID")
	private Integer formID;

	// @Column(name = "USER_READ")
	// boolean userRead;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BOOK_ID")
	private Book book;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Override
	public String toString() {
		return "Form [formID=" + formID + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Form other = (Form) obj;
		if (formID == null) {
			if (other.formID != null)
				return false;
		} else if (!formID.equals(other.formID))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formID == null) ? 0 : formID.hashCode());
		return result;
	}
}
