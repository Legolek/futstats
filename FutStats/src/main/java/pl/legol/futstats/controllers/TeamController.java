package pl.legol.futstats.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.legol.futstats.model.dto.TeamDto;
import pl.legol.futstats.service.TeamService;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TeamDto> getAllTeams() {
        return teamService.getAllTeams();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addTeam(@RequestBody TeamDto team) {
        teamService.addNewTeam(team);
    }
}
