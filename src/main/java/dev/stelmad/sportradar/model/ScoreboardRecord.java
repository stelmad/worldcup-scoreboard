package dev.stelmad.sportradar.model;

import lombok.Data;

@Data
public class ScoreboardRecord {
    private String id;

    private String homeTeamName;
    private int homeTeamScore;

    private String awayTeamName;
    private int awayTeamScore;
}
