package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import dev.stelmad.sportradar.model.ScoreboardRecord;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class WorldCupScoreboardImpl implements Scoreboard {

    private static final String MATCH_ID_TEMPLATE = "%svs%s";

    // Set of all playing teams to track duplicates
    private final Set<String> playingTeams = new HashSet<>();

    private final Map<String, ScoreboardRecord> scoreboard = new LinkedHashMap<>();

    /**
     * Creates a new match record with the given team names and an initial score of 0-0.
     * Tracks playing teams to ensure no team is in more than one match.
     * Converts team names to uppercase for consistency.
     *
     * @param homeTeamName the home team name
     * @param awayTeamName the away team name
     * @return the match ID
     * @throws IllegalArgumentException if a team is already playing or if arguments are empty
     */
    @Override
    public String startMatch(String homeTeamName, String awayTeamName) {
        if (StringUtils.isEmpty(homeTeamName) || StringUtils.isEmpty(awayTeamName)) {
            throw new IllegalArgumentException("The method arguments can't be empty.");
        }

        if (StringUtils.equals(homeTeamName, awayTeamName)) {
            throw new IllegalArgumentException("The arguments can't be equal.");
        }

        final String homeTeam = homeTeamName.toUpperCase();
        final String awayTeam = awayTeamName.toUpperCase();
        if (this.playingTeams.contains(homeTeam) || this.playingTeams.contains(awayTeam)) {
            throw new IllegalArgumentException("One of the provided teams already has a live match.");
        }

        final String matchId = String.format(MATCH_ID_TEMPLATE, homeTeam, awayTeam);
        this.scoreboard.put(matchId, new ScoreboardRecord(homeTeam, awayTeam));
        this.playingTeams.add(homeTeam);
        this.playingTeams.add(awayTeam);
        return matchId;
    }

    @Override
    public ScoreboardRecord finishMatch(String matchId) {
        throw new UnsupportedOperationException();
    }
}
