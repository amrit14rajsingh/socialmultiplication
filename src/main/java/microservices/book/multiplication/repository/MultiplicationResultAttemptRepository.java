package microservices.book.multiplication.repository;

import microservices.book.multiplication.domain.MultiplicationResultAttempt;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface allow us to store and retrieve attempts
 */
@Repository
public interface MultiplicationResultAttemptRepository
        extends CrudRepository<MultiplicationResultAttempt, Long> {

    /**
     * @return the latest 5 attempts for a given user, identified by their alias.
     */
    List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);
}