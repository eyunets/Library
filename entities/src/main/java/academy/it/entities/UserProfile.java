package academy.it.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import academy.it.entities.enums.UserProfileType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_PROFILE_ID")
	private Integer userProfileID;

	@Column(name = "TYPE", length = 15, unique = true, nullable = false)
	private String type = UserProfileType.USER.getType();

	@Override
	public String toString() {
		return "UserProfile [id=" + userProfileID + ",  type=" + type + "]";
	}
}
