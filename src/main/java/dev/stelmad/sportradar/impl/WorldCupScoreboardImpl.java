package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import dev.stelmad.sportradar.model.ScoreboardRecord;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

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

    /**
     * Finishes a match and removes it from the scoreboard.
     * Also removes the teams from the list of playing teams.
     *
     * @param matchId the ID of the match to finish
     * @return the record of the finished match, or null if the match ID does not exist
     * @throws IllegalArgumentException if the match ID is empty
     */
    @Override
    public ScoreboardRecord finishMatch(String matchId) {
        if (StringUtils.isEmpty(matchId)) {
            throw new IllegalArgumentException("The method argument can't be empty.");
        }

        if (this.scoreboard.containsKey(matchId)) {
            final ScoreboardRecord record = this.scoreboard.remove(matchId);
            this.playingTeams.remove(record.getHomeTeamName());
            this.playingTeams.remove(record.getAwayTeamName());
            return record;
        }
        return null;
    }

    /**
     * Updates the score of an ongoing match.
     *
     * @param matchId       the ID of the match to update
     * @param homeTeamScore the new score for the home team
     * @param awayTeamScore the new score for the away team
     * @return true if the match was found and updated, false otherwise
     * @throws IllegalArgumentException if the match ID is empty or if any score is negative
     */
    @Override
    public boolean updateMatch(String matchId, int homeTeamScore, int awayTeamScore) {
        if (homeTeamScore < 0 || awayTeamScore < 0) {
            throw new IllegalArgumentException("Score values can't be negative.");
        }

        if (StringUtils.isEmpty(matchId)) {
            throw new IllegalArgumentException("The matchId argument can't be empty.");
        }

        if (this.scoreboard.containsKey(matchId)) {
            final ScoreboardRecord record = this.scoreboard.get(matchId);
            record.setHomeTeamScore(homeTeamScore);
            record.setAwayTeamScore(awayTeamScore);
            return true;
        }
        return false;
    }

    @Override
    public List<ScoreboardRecord> getRecordList() {
        throw new UnsupportedOperationException();
    }
}
