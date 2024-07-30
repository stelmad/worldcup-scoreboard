package dev.stelmad.sportradar;

import dev.stelmad.sportradar.impl.TotalScoreSummaryProviderImpl;
import dev.stelmad.sportradar.impl.WorldCupScoreboardImpl;
import dev.stelmad.sportradar.model.ScoreboardRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WorldCupScoreboardIT {
    private static final Scoreboard SCOREBOARD = new WorldCupScoreboardImpl();
    private static final SummaryProvider SUMMARY_PROVIDER = new TotalScoreSummaryProviderImpl(SCOREBOARD);

    @Test
    void worldCupDay1_IT() {
        final String nedusaID = SCOREBOARD.startMatch("Netherlands", "USA");
        final String argausID = SCOREBOARD.startMatch("Argentina", "Australia");

        assertAll(
            () -> assertTrue(SCOREBOARD.updateMatch(nedusaID, 3, 1)),
            () -> assertTrue(SCOREBOARD.updateMatch(argausID, 2, 1))
        );

        final List<ScoreboardRecord> summaryFirstRound = SUMMARY_PROVIDER.getSummary();
        assertAll(
            () -> assertEquals(2, summaryFirstRound.size()),
            () -> {
                ScoreboardRecord record = summaryFirstRound.get(0);
                assertEquals("NETHERLANDS", record.getHomeTeamName());
                assertEquals("USA", record.getAwayTeamName());
            },
            () -> {
                ScoreboardRecord record = summaryFirstRound.get(1);
                assertEquals("ARGENTINA", record.getHomeTeamName());
                assertEquals("AUSTRALIA", record.getAwayTeamName());
            }
        );

        assertAll(
            () -> {
                ScoreboardRecord result = SCOREBOARD.finishMatch(argausID);
                assertEquals(2, result.getHomeTeamScore());
                assertEquals(1, result.getAwayTeamScore());
            },
            () -> {
                ScoreboardRecord result = SCOREBOARD.finishMatch(nedusaID);
                assertEquals(3, result.getHomeTeamScore());
                assertEquals(1, result.getAwayTeamScore());
            }
        );

        assertTrue(SUMMARY_PROVIDER.getSummary().isEmpty());

        // DAY #2


    }

    @Test
    void worldCupDay2_IT() {
        final String frapolID = SCOREBOARD.startMatch("France", "Poland");
        SCOREBOARD.updateMatch(frapolID, 3, 1);

        final String engsenID = SCOREBOARD.startMatch("England", "Senegal");
        SCOREBOARD.updateMatch(engsenID, 3, 0);
        SCOREBOARD.finishMatch(engsenID);

        final String japcroID = SCOREBOARD.startMatch("Japan", "Croatia");
        SCOREBOARD.updateMatch(japcroID, 1, 3);

        final String braskrID = SCOREBOARD.startMatch("Brazil", "South Korea");
        SCOREBOARD.updateMatch(braskrID, 4, 1);

        final List<ScoreboardRecord> summarySecondRound = SUMMARY_PROVIDER.getSummary();
        assertAll(
            () -> assertEquals(3, summarySecondRound.size()),
            () -> {
                ScoreboardRecord record = summarySecondRound.get(0);
                assertEquals("BRAZIL", record.getHomeTeamName());
                assertEquals("SOUTH KOREA", record.getAwayTeamName());
            },
            () -> {
                ScoreboardRecord record = summarySecondRound.get(1);
                assertEquals("JAPAN", record.getHomeTeamName());
                assertEquals("CROATIA", record.getAwayTeamName());
            },
            () -> {
                ScoreboardRecord record = summarySecondRound.get(2);
                assertEquals("FRANCE", record.getHomeTeamName());
                assertEquals("POLAND", record.getAwayTeamName());
            }
        );

        SCOREBOARD.finishMatch(frapolID);
        SCOREBOARD.finishMatch(japcroID);
        SCOREBOARD.finishMatch(braskrID);

        assertTrue(SUMMARY_PROVIDER.getSummary().isEmpty());
    }
}
