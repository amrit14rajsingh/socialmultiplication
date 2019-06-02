package microservices.book.multiplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;*/


/*@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode*/
@Entity
public class Multiplication {
	
	@Id
	@GeneratedValue
	@Column(name = "MULTIPLICATION_ID")
	private Long id;
    // Both factors
    private int factorA;
    private int factorB;

    // The result of the operation A * B
    private int result;
    
    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
        this.result = factorA * factorB;
    }
    public Multiplication() {
        this.factorA = 0;
        this.factorB = 0;
        this.result = 0;
    }
    public int getFactorA() {
		// TODO Auto-generated method stub
		return factorA;
	}
	public int getFactorB() {
		// TODO Auto-generated method stub
		return factorB;
	}
	public int getResult() {
		// TODO Auto-generated method stub
		return result;
	}
	public Long getId() {
		return id;
	}
}
