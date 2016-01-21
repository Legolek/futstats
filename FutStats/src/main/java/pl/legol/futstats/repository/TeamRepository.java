package pl.legol.futstats.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import pl.legol.futstats.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long>, JpaSpecificationExecutor<Team> {
    public Team findBySearchNameIgnoringCaseContaining(String name);

    public Team findByName(String name);
}
