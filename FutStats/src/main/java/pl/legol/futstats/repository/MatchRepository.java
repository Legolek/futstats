package pl.legol.futstats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import pl.legol.futstats.model.Match;

public interface MatchRepository extends CrudRepository<Match, Long>, JpaSpecificationExecutor<Match> {

    @Query("select m from Match m where m.homeTeam.id=?1 or m.awayTeam.id=?1")
    public List<Match> findByTeam(Long id);
}
