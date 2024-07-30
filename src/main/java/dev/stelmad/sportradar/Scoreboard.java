package dev.stelmad.sportradar;

import dev.stelmad.sportradar.model.ScoreboardRecord;

import java.util.List;

public interface Scoreboard {
    String startMatch(String homeTeamName, String awayTeamName);

    ScoreboardRecord finishMatch(String matchId);

    boolean updateMatch(String matchId, int homeTeamScore, int awayTeamScore);

    List<ScoreboardRecord> getRecordList();
}
