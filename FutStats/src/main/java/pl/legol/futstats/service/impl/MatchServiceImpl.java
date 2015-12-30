package pl.legol.futstats.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.legol.futstats.model.Match;
import pl.legol.futstats.model.dto.MatchDto;
import pl.legol.futstats.model.dto.TeamDto;
import pl.legol.futstats.repository.MatchRepository;
import pl.legol.futstats.service.MatchService;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public void addNewMatch(MatchDto match) {
        matchRepository.save(mapper.map(match, Match.class));
    }

    @Override
    public List<MatchDto> getAll() {
        return StreamSupport
                .stream(matchRepository.findAll().spliterator(), false)
                .map((m) -> mapper.map(m, MatchDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MatchDto> findMatchesForTeam(TeamDto team) {
        return StreamSupport
                .stream(matchRepository.findByTeam(team.getId()).spliterator(), false)
                .map((m) -> mapper.map(m, MatchDto.class))
                .collect(Collectors.toList());
    }
}
