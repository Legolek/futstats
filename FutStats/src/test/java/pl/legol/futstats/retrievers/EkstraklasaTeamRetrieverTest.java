package pl.legol.futstats.retrievers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pl.legol.futstats.model.dto.TeamDto;

public class EkstraklasaTeamRetrieverTest {

    private static final String BODY = "alt=\"Termalica Bruk-Bet Nieciecza\" border=\"0\"><br>Termalica Bruk-Bet Nieciecza</a></td><td height=\"150\"";

    @InjectMocks
    private EkstraklasaTeamRetriever teamRetriever;

    @BeforeMethod
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveTeams() {
        List<TeamDto> teams = teamRetriever.retrieveTeams(BODY);
        assertThat(teams).hasSize(1);
        assertThat("Termalica Bruk-Bet Nieciecza").isEqualTo(teams.get(0).getName());
        assertThat("termalicabrukbetnieciecza").isEqualTo(teams.get(0).getSearchName());
    }

}
