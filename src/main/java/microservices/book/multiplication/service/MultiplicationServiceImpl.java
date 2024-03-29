  package microservices.book.multiplication.service;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import microservices.book.multiplication.domain.User;
import microservices.book.multiplication.event.EventDispatcher;
import microservices.book.multiplication.event.MultiplicationSolvedEvent;
import microservices.book.multiplication.repository.MultiplicationResultAttemptRepository;
import microservices.book.multiplication.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
class MultiplicationServiceImpl implements MultiplicationService {

    private RandomGeneratorService randomGeneratorService;
    private MultiplicationResultAttemptRepository attemptRepository;
    private UserRepository userRepository;
    private EventDispatcher eventDispatcher;

    @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
            final MultiplicationResultAttemptRepository attemptRepository,
            final UserRepository userRepository,
            final EventDispatcher eventDispatcher) {
    	this.randomGeneratorService = randomGeneratorService;
    	this.attemptRepository = attemptRepository;
    	this.userRepository = userRepository;
    	this.eventDispatcher = eventDispatcher;
    }
    
   /* @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService) {
    	this.randomGeneratorService = randomGeneratorService;
    }*/


    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB);
    }
    
    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationResultAttempt attempt) {
    	// Check if the user already exists for that alias
    	Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());
    	
    	// Avoids 'hack' attempts
        Assert.isTrue(!attempt.getCorrect(), "You can't send an attempt marked as correct!!");

        boolean isCorrect = attempt.getResultAttempt() ==
        		attempt.getMultiplication().getFactorA() *
        		attempt.getMultiplication().getFactorB();
        
        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user.orElse(attempt.getUser()),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                isCorrect
        );
        // Stores the attempt
        attemptRepository.save(checkedAttempt);
        
        //Communicate result via event
        eventDispatcher.send(new MultiplicationSolvedEvent(checkedAttempt.getId(), checkedAttempt.getUser().getId(), checkedAttempt.getCorrect()));
        
        return isCorrect;
    }

	@Override
	public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
		// TODO Auto-generated method stub
		return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
	}

	@Override
	public MultiplicationResultAttempt getResultById(Long resultId) {
		// TODO Auto-generated method stub
		//return attemptRepository.findById(resultId);
		return null;
	}
} 