package pl.legol.futstats.model.dto;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.dozer.Mapping;

public class MatchDto {

    @Mapping("id")
    private Long id;

    @Mapping("homeTeam")
    private TeamDto homeTeam;

    @Mapping("awayTeam")
    private TeamDto awayTeam;

    @Mapping("homeScore")
    private Integer homeScore;

    @Mapping("awayScore")
    private Integer awayScore;

    @Mapping("matchDate")
    private Date matchDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamDto getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(TeamDto homeTeam) {
        this.homeTeam = homeTeam;
    }

    public TeamDto getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(TeamDto awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(id)
                .append(homeTeam.getName())
                .append(awayTeam.getName())
                .append(homeScore)
                .append(awayScore)
                .append(matchDate)
                .build();
    }

    public static class Builder {
        private MatchDto inner;

        public Builder() {
            inner = new MatchDto();
        }

        public Builder between(TeamDto homeTeam, TeamDto awayTeam) {
            inner.setHomeTeam(homeTeam);
            inner.setAwayTeam(awayTeam);
            return this;
        }

        public Builder result(Integer homeScore, Integer awayScore) {
            inner.setHomeScore(homeScore);
            inner.setAwayScore(awayScore);
            return this;
        }

        public Builder at(Date matchDate) {
            inner.setMatchDate(matchDate);
            return this;
        }

        public MatchDto build() {
            return inner;
        }
    }

}
