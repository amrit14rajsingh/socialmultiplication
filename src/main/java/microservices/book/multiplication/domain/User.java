package microservices.book.multiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
*/
/**
 * Stores information to identify the user.
 */
/*@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode*/
@Entity
public final class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
    private final String alias;

    
	// Empty constructor for JSON (de)serialization
    protected User() {
        alias = null;
    }
    
    public User(String alias) {
        this.alias = alias;
    }
    
    public String getAlias() {
		return alias;
	}
    public Long getId() {
		return this.id;
	}
}