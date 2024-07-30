package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import dev.stelmad.sportradar.model.ScoreboardRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldCupScoreboardImplTest {

    @Test
    void startMatchTest() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        assertAll(
            () -> assertEquals("MEXICOvsCANADA", scoreboard.startMatch("Mexico", "Canada")),
            () -> assertEquals("SPAINvsBRAZIL", scoreboard.startMatch("Spain", "Brazil")),
            () -> assertEquals("GERMANYvsFRANCE", scoreboard.startMatch("Germany", "France")),
            () -> assertEquals("URUGUAYvsITALY", scoreboard.startMatch("Uruguay", "Italy")),
            () -> assertEquals("ARGENTINAvsAUSTRALIA", scoreboard.startMatch("Argentina", "Australia"))
        );
    }

    @Test
    void startMatchTestWithDuplicates() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        assertAll(
            () -> assertEquals("MEXICOvsCANADA", scoreboard.startMatch("Mexico", "Canada")),
            () -> assertEquals("SPAINvsBRAZIL", scoreboard.startMatch("Spain", "Brazil")),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", "Canada")),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Spain", "Brazil"))
        );
    }

    @Test
    void startMatchTestWithInvalidArgs() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, null)),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch(null, "Mexico")),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Spain", null)),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Spain", "")),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("", "Mexico")),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.startMatch("Mexico", "Mexico"))
        );
    }

    @Test
    void finishMatchTest() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        scoreboard.startMatch("Mexico", "Canada");
        assertAll(
            () -> assertNull(scoreboard.finishMatch("noExistingMatchID")),
            () -> {
                ScoreboardRecord record = scoreboard.finishMatch("MEXICOvsCANADA");
                assertNotNull(record);
                assertEquals("MEXICO", record.getHomeTeamName());
                assertEquals("CANADA", record.getAwayTeamName());
                assertEquals(0, record.getHomeTeamScore());
                assertEquals(0, record.getAwayTeamScore());
            }
        );
    }

    @Test
    void finishMatchTestWithInvalidArgs() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.finishMatch(null)),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.finishMatch(""))
        );
    }

    @Test
    void updateMatchTest() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        scoreboard.startMatch("Mexico", "Canada");
        assertAll(
            () -> assertFalse(scoreboard.updateMatch("noExistingMatchID", 2, 2)),
            () -> assertTrue(scoreboard.updateMatch("MEXICOvsCANADA", 2, 2))
        );
    }

    @Test
    void updateMatchTestWithInvalidArgs() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.updateMatch(null, 0, 0)),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.updateMatch("MEXICOvsCANADA", -1, 2)),
            () -> assertThrows(IllegalArgumentException.class, () -> scoreboard.updateMatch("MEXICOvsCANADA", 4, -5))
        );
    }


}