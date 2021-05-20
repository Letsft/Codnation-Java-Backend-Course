package challenge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Integer> {

    @Query(value = "SELECT * FROM SCRIPTS S " +
            "ORDER BY RAND() " +
            "LIMIT 1 ", nativeQuery = true)
    Quote findOrderByRandom();

    @Query(value = "SELECT * FROM SCRIPTS S " +
            "WHERE S.ACTOR = :actor " +
            "ORDER BY RAND() " +
            "LIMIT 1 ", nativeQuery = true)
    Quote findByActorOrderByRandom(@Param("actor") String actor);

}
