package pl.legol.futstats.service;

import java.util.List;

import javax.transaction.Transactional;

import pl.legol.futstats.model.dto.MatchDto;
import pl.legol.futstats.model.dto.TeamDto;

public interface MatchService {

    public List<MatchDto> getAll();

    public List<MatchDto> findMatchesForTeam(TeamDto team);

    @Transactional
    public void addNewMatch(MatchDto match);
}
