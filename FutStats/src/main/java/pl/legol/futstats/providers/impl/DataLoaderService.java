package pl.legol.futstats.providers.impl;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.legol.futstats.model.Match;
import pl.legol.futstats.model.Team;
import pl.legol.futstats.model.dto.MatchDto;
import pl.legol.futstats.model.dto.TeamDto;
import pl.legol.futstats.providers.DataProvider;
import pl.legol.futstats.repository.MatchRepository;
import pl.legol.futstats.repository.TeamRepository;

@Service
public class DataLoaderService {

    private Logger log = LoggerFactory.getLogger(DataLoaderService.class);

    @Autowired
    private DataProvider dataProvider;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private Mapper mapper;

    public void retrieveData() {
        log.info("Retrieveing data...");
        dataProvider.loadTeams().thenAcceptBoth(dataProvider.loadMatches(), this::saveData);
    }

    @Transactional(value = TxType.REQUIRED)
    private void saveData(List<TeamDto> teams, List<MatchDto> matches) {
        for (TeamDto teamDto : teams) {
            Team teamEntity = mapper.map(teamDto, Team.class);
            teamRepository.save(teamEntity);
        }

        for (MatchDto matchDto : matches) {
            Match matchEntity = mapper.map(matchDto, Match.class);
            matchEntity.setHomeTeam(teamRepository.findByName(matchDto.getHomeTeam().getName()));
            matchEntity.setAwayTeam(teamRepository.findByName(matchDto.getAwayTeam().getName()));
            matchRepository.save(matchEntity);
        }
    }

}
