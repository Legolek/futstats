package pl.legol.futstats.providers.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.legol.futstats.model.dto.MatchDto;
import pl.legol.futstats.model.dto.TeamDto;
import pl.legol.futstats.providers.DataProvider;
import pl.legol.futstats.retrievers.MatchRetriever;
import pl.legol.futstats.retrievers.TeamRetriever;
import pl.legol.futstats.service.ext.NinetyMinutesFacade;

@Service
public class EkstraklasaDataProvider implements DataProvider {
    private Logger log = LoggerFactory.getLogger(EkstraklasaDataProvider.class);

    @Autowired
    private NinetyMinutesFacade ninetyMinutesFacade;

    @Autowired
    private TeamRetriever teamRetriever;

    @Autowired
    private MatchRetriever matchRetriever;

    @Override
    public CompletableFuture<List<TeamDto>> loadTeams() {
        return CompletableFuture.supplyAsync(() -> {
            List<TeamDto> teams = new ArrayList<TeamDto>();
            String responseBody = ninetyMinutesFacade.loadTeams();
            teams.addAll(teamRetriever.retrieveTeams(responseBody));
            return teams;
        });
    }

    @Override
    public CompletableFuture<List<MatchDto>> loadMatches() {
        return CompletableFuture.supplyAsync(() -> {
            List<MatchDto> matches = new ArrayList<>();
            String responseBody = ninetyMinutesFacade.loadMatches();
            matches.addAll(matchRetriever.retrieveMatches(responseBody));
            return matches;
        });
    }

}
