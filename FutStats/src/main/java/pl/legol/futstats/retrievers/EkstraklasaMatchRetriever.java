package pl.legol.futstats.retrievers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import pl.legol.futstats.model.dto.MatchDto;
import pl.legol.futstats.model.dto.TeamDto;

@Component
public class EkstraklasaMatchRetriever implements MatchRetriever {

    private static final String MATCH_PATTERN = "<a href=\"\\/mecz.php\\?id_mecz=[\\d]+\" class=\"main\" title=\"([\\wżźćółńęąŻŹÓĆŁŃŚĄĘ -]+) ([\\d]+)-([\\d]+) ([\\wżźćółńęąŻŹÓĆŁŃŚĄĘ -]+)\">";

    @Override
    public List<MatchDto> retrieveMatches(String body) {
        List<MatchDto> matches = new ArrayList<>();
        Scanner sc = new Scanner(body);
        Pattern p = Pattern.compile(MATCH_PATTERN);
        while (sc.hasNextLine()) {
            Matcher m = p.matcher(sc.nextLine());
            while (m.find()) {
                TeamDto homeTeam = new TeamDto.Builder().named(m.group(1), null).build();
                TeamDto awayTeam = new TeamDto.Builder().named(m.group(4), null).build();
                MatchDto match = new MatchDto.Builder()
                        .between(homeTeam, awayTeam)
                        .result(Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)))
                        .build();
                matches.add(match);
            }
        }
        return matches;
    }
}
