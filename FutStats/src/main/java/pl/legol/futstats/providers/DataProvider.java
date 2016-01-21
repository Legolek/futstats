package pl.legol.futstats.providers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import pl.legol.futstats.model.dto.MatchDto;
import pl.legol.futstats.model.dto.TeamDto;

public interface DataProvider {
    CompletableFuture<List<TeamDto>> loadTeams();

    CompletableFuture<List<MatchDto>> loadMatches();
}
