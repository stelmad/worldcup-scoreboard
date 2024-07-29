package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import dev.stelmad.sportradar.model.ScoreboardRecord;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class WorldCupScoreboardImpl implements Scoreboard {

    private static final String KEY_TEMPLATE = "%svs%s";

    private final Set<String> playingTeams = new HashSet<>();
    private final Map<String, ScoreboardRecord> scoreboard = new LinkedHashMap<>();

    @Override
    public String startMatch(String homeTeamName, String awayTeamName) {
        final String homeTeam = homeTeamName.toUpperCase();
        final String awayTeam = awayTeamName.toUpperCase();
        if (this.playingTeams.contains(homeTeam) || this.playingTeams.contains(awayTeam)) {
            throw new IllegalArgumentException("One of the provided teams already has a live match.");
        }

        final String key = String.format(KEY_TEMPLATE, homeTeam, awayTeam);
        this.scoreboard.put(key, new ScoreboardRecord(homeTeam, awayTeam));
        this.playingTeams.add(homeTeam);
        this.playingTeams.add(awayTeam);
        return key;
    }
}
