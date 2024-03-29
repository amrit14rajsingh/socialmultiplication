package microservices.book.multiplication.repository;

import microservices.book.multiplication.domain.Multiplication;
import microservices.book.multiplication.domain.MultiplicationResultAttempt;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface allows us to save and retrieve Multiplications
 */
@Repository
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
	/**
     * @return the latest 5 attempts for a given user, identified by their alias.
     */
	
	//List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}


