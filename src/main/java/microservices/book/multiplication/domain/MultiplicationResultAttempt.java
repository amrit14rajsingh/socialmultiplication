package microservices.book.multiplication.domain;
/*import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;*/

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import microservices.book.multiplication.domain.User;
//import javax.persistence.*;


/**
 * Identifies the attempt from a {@link User} to solve a
 * {@link Multiplication}.
 */
/*@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode*/
@Entity
public final class MultiplicationResultAttempt {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "USER_ID")
    private final User user;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "MULTIPLICATION_ID")
    private final Multiplication multiplication;
    private final int resultAttempt;
    private final boolean correct;
    
    public User getUser() {
		return user;
	}

	public Multiplication getMultiplication() {
		return multiplication;
	}

	public int getResultAttempt() {
		return resultAttempt;
	}
	
	public boolean getCorrect() {
		return correct;
	}
	
	public Long getId() {
		return id;
	}
	// Empty constructor for JSON (de)serialization
	 MultiplicationResultAttempt() {
		    id = 0L;
	        user = null;
	        multiplication = null;
	        resultAttempt = -1;
	        correct = false;
	    }
	public MultiplicationResultAttempt(User user, Multiplication multiplication, int resultAttempt,boolean correct){
    	this.user = user;
        this.multiplication = multiplication;
        this.resultAttempt = resultAttempt;
        this.correct = correct;
    }

    
}