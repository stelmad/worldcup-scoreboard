package dev.stelmad.sportradar.model;

import lombok.Data;

@Data
public class ScoreboardRecord {
    private final String homeTeamName;
    private final String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;

    public int getTotalScore() {
        return this.homeTeamScore + this.awayTeamScore;
    }
}
