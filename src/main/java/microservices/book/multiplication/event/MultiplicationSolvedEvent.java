package microservices.book.multiplication.event;

import java.io.Serializable;

public class MultiplicationSolvedEvent implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long multiplicationResultAttemptId;
  	private final Long userId;
    private final boolean correct;
    
 // Empty constructor for JSON (de)serialization
    public MultiplicationSolvedEvent() {
		this.userId = 0L;
		this.correct = false;
		this.multiplicationResultAttemptId = 0L;
	}
    
	public MultiplicationSolvedEvent(Long multiplicationResultAttemptId, Long userId, boolean correct) {
		super();
		this.multiplicationResultAttemptId = multiplicationResultAttemptId;
		this.userId = userId;
		this.correct = correct;
	}
    
    public Long getMultiplicationResultAttemptId() {
  		return multiplicationResultAttemptId;
  	}
  	public Long getUserId() {
  		return userId;
  	}
  	public boolean getCorrect() {
  		return correct;
  	}

}
