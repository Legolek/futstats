package pl.legol.futstats.retrievers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pl.legol.futstats.model.dto.MatchDto;

public class EkstraklasaMatchRetrieverTest {

    private static final String BODY = "<a href=\"/mecz.php?id_mecz=1138914\" class=\"main\" title=\"Cracovia 2-1 Jagiellonia Białystok\">1-1</a>";

    @InjectMocks
    private EkstraklasaMatchRetriever ekstraklasaMatchRetriever;

    @BeforeMethod
    public void beforeMethod() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveMatches() {
        List<MatchDto> matches = ekstraklasaMatchRetriever.retrieveMatches(BODY);
        assertThat(matches).hasSize(1);
        MatchDto match = matches.get(0);
        assertThat(match).isNotNull();
        assertThat(match.getHomeTeam()).isNotNull();
        assertThat(match.getAwayTeam()).isNotNull();
        assertThat(match.getAwayScore()).isEqualTo(1);
        assertThat(match.getHomeScore()).isEqualTo(2);
        assertThat(match.getHomeTeam().getName()).isEqualTo("Cracovia");
        assertThat(match.getAwayTeam().getName()).isEqualTo("Jagiellonia Białystok");
    }
}
