package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.event.EventDispatcher;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

public class MultiplicationServiceImplTest{

    private MultiplicationServiceImpl multiplicationServiceImpl;

    @Mock  
    private RandomGeneratorService randomGeneratorService;
    
    @Mock
    private MultiplicationResultAttemptRepository attemptRepository;
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private EventDispatcher eventDispatcher;

    @Before
    public void setUp() {
        // With this call to initMocks we tell Mockito to process the annotations 
        MockitoAnnotations.initMocks(this);
        multiplicationServiceImpl = new MultiplicationServiceImpl(randomGeneratorService,attemptRepository,
        		                                                 userRepository,eventDispatcher);
    }

    @Test
    public void createRandomMultiplicationTest() {
        // given (our mocked Random Generator service will return first 50, then 30)
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        Multiplication multiplication = multiplicationServiceImpl.createRandomMultiplication();

        // assert
        assertThat(multiplication.getFactorA()).isEqualTo(50);
        assertThat(multiplication.getFactorB()).isEqualTo(30);
        assertThat(multiplication.getResult()).isEqualTo(1500);
    }
    
    @Test
    public void checkCorrectAttemptTest() {
    	Multiplication multiplication = new Multiplication(50,60);
    	User user = new User("john_doe");
    	MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user,multiplication,3000,false);
    	MultiplicationResultAttempt verifyAttempt = new MultiplicationResultAttempt(user,multiplication,
    			                                                                    3000,true);
    	given(userRepository.findByAlias("john_doe")).willReturn(Optional.empty());
    	//when
    	boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
    	//then
    	assertThat(attemptResult).isTrue();
    	verify(attemptRepository).save(verifyAttempt);
    	
    } 
    
    @Test
    public void checkWrongAttemptTest() {
    	Multiplication multiplication = new Multiplication(50,60);
    	User user = new User("Apple Bee");
    	MultiplicationResultAttempt attempt = new MultiplicationResultAttempt(user,multiplication,3001,false  );
    	boolean attemptResult = multiplicationServiceImpl.checkAttempt(attempt);
    	assertThat(attemptResult).isFalse();
    	
    }
}