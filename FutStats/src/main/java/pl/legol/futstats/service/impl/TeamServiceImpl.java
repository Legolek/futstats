package pl.legol.futstats.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.legol.futstats.model.Team;
import pl.legol.futstats.model.dto.TeamDto;
import pl.legol.futstats.repository.TeamRepository;
import pl.legol.futstats.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public List<TeamDto> getAllTeams() {
        return StreamSupport
                .stream(teamRepository.findAll().spliterator(), false)
                .map((t) -> mapper.map(t, TeamDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long addNewTeam(TeamDto team) {
        Team teamToPersist = mapper.map(team, Team.class);
        teamRepository.save(teamToPersist);
        return teamToPersist.getId();
    }

    @Override
    public Optional<TeamDto> getTeamWithName(String name) {
        Team team = teamRepository.findBySearchNameIgnoringCaseContaining(name);
        if (team != null) {
            return Optional.ofNullable(mapper.map(team, TeamDto.class));
        }
        return Optional.ofNullable(null);
    }
}
