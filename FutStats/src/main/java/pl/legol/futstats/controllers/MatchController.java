package pl.legol.futstats.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.legol.futstats.model.dto.MatchDto;
import pl.legol.futstats.model.dto.TeamDto;
import pl.legol.futstats.service.MatchService;
import pl.legol.futstats.service.TeamService;

@RestController
@RequestMapping(value = "/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    public List<MatchDto> getAll() {
        return matchService.getAll();
    }

    @RequestMapping(value = "/{team}")
    public List<MatchDto> getMatchesForTeam(@PathVariable String team) {
        List<MatchDto> toRet = new ArrayList<>();
        Optional<TeamDto> optTeam = teamService.getTeamWithName(team);
        if (optTeam.isPresent()) {
            toRet.addAll(matchService.findMatchesForTeam(optTeam.get()));
        }
        return toRet;
    }

}
