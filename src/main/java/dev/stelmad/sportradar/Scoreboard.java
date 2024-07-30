package dev.stelmad.sportradar;

import dev.stelmad.sportradar.model.ScoreboardRecord;

public interface Scoreboard {
    String startMatch(String homeTeamName, String awayTeamName);

    ScoreboardRecord finishMatch(String matchId);

    boolean updateMatch(String matchId, int homeTeamScore, int awayTeamScore);
}
