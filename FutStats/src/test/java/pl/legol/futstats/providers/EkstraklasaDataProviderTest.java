package pl.legol.futstats.providers;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import pl.legol.futstats.providers.impl.EkstraklasaDataProvider;
import pl.legol.futstats.retrievers.EkstraklasaTeamRetriever;
import pl.legol.futstats.service.ext.NinetyMinutesFacade;

@RunWith(MockitoJUnitRunner.class)
public class EkstraklasaDataProviderTest {

    private static final String BODY = "alt=\"Termalica Bruk-Bet Nieciecza\" border=\"0\"><br>Termalica Bruk-Bet Nieciecza</a></td><td height=\"150\"";

    @InjectMocks
    private EkstraklasaDataProvider ekstraklasaDataProvider;

    @Mock
    private EkstraklasaTeamRetriever ekstraklasaTeamRetriever;

    @Mock
    private NinetyMinutesFacade ninetyMinutesFacade;

    @Before
    public void beforeTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoadTeams() {
        Mockito.when(ninetyMinutesFacade.loadTeams()).thenReturn(BODY);
        Mockito.when(ekstraklasaTeamRetriever.retrieveTeams(BODY)).thenReturn(Collections.EMPTY_LIST);

        ekstraklasaDataProvider.loadTeams();

        Mockito.verify(ninetyMinutesFacade).loadTeams();
        Mockito.verify(ekstraklasaTeamRetriever).retrieveTeams(BODY);
    }
}
