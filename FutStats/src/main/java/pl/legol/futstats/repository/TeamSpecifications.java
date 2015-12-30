package pl.legol.futstats.repository;

import org.springframework.data.jpa.domain.Specification;

import pl.legol.futstats.model.Team;
import pl.legol.futstats.model.Team_;

public class TeamSpecifications {
    public static Specification<Team> fromCracow() {
        return (root, query, cb) -> {
            return cb.like(root.get(Team_.name), "%Kraków");
        };
    }
}
