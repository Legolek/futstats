package pl.legol.futstats.retrievers;

import java.util.List;

import pl.legol.futstats.model.dto.TeamDto;

public interface TeamRetriever {
    List<TeamDto> retrieveTeams(String body);
}
