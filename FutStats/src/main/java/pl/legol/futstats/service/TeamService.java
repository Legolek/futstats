package pl.legol.futstats.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import pl.legol.futstats.model.dto.TeamDto;

public interface TeamService {

    public List<TeamDto> getAllTeams();

    public Optional<TeamDto> getTeamWithName(String name);

    @Transactional(value = TxType.REQUIRED)
    public Long addNewTeam(TeamDto team);
}
