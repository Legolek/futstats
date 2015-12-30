package pl.legol;

import java.util.Calendar;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import pl.legol.futstats.model.dto.MatchDto;
import pl.legol.futstats.model.dto.TeamDto;
import pl.legol.futstats.service.MatchService;
import pl.legol.futstats.service.TeamService;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class FutStatsApplication {

    @Bean
    public InitializingBean testData(TeamService teamService, MatchService matchService) {
        return () -> {
            TeamDto wisla = new TeamDto.Builder().named("Wisła Kraków", "wislakrakow").build();
            TeamDto legia = new TeamDto.Builder().named("Legia Warszawa", "legiawarszawa").build();
            TeamDto cracovia = new TeamDto.Builder().named("Cracovia Kraków", "cracoviakrakow").build();
            wisla.setId(teamService.addNewTeam(wisla));
            legia.setId(teamService.addNewTeam(legia));
            cracovia.setId(teamService.addNewTeam(cracovia));

            MatchDto match1 = new MatchDto.Builder()
                    .between(wisla, legia)
                    .result(2, 0)
                    .at(Calendar.getInstance().getTime())
                    .build();

            matchService.addNewMatch(match1);

            MatchDto match2 = new MatchDto.Builder()
                    .between(wisla, cracovia)
                    .result(5, 1)
                    .at(Calendar.getInstance().getTime())
                    .build();

            matchService.addNewMatch(match2);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FutStatsApplication.class, args);
    }
}
