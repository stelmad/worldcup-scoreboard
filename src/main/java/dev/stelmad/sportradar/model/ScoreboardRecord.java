package dev.stelmad.sportradar.model;

import lombok.Data;

@Data
public class ScoreboardRecord {
    private final String homeTeamName;
    private int homeTeamScore;

    private final String awayTeamName;
    private int awayTeamScore;
}
