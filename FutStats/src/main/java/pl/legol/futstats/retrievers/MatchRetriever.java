package pl.legol.futstats.retrievers;

import java.util.List;

import pl.legol.futstats.model.dto.MatchDto;

public interface MatchRetriever {
    public List<MatchDto> retrieveMatches(String body);
}
