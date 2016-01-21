package pl.legol.futstats.retrievers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import pl.legol.futstats.model.dto.TeamDto;

@Component
public class EkstraklasaTeamRetriever implements TeamRetriever {

    private static final String TEAM_PATTERN = "<br>([\\wżźćółńęąŻŹÓĆŁŃŚĄĘ -]*)</a>";

    @Override
    public List<TeamDto> retrieveTeams(String body) {
        List<TeamDto> teams = new ArrayList<>();
        Scanner sc = new Scanner(body);
        Pattern p = Pattern.compile(TEAM_PATTERN);
        while (sc.hasNextLine()) {
            Matcher m = p.matcher(sc.nextLine());
            while (m.find()) {
                String teamName = m.group(1);
                TeamDto team = new TeamDto.Builder().named(teamName, buildSearchName(teamName)).build();
                teams.add(team);
            }
        }
        return teams;
    }

    private String buildSearchName(String name) {
        return name.replace(" ", "").replace("-", "").toLowerCase();
    }
}
