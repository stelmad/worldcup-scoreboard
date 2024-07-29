package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldCupScoreboardImplTest {

    @Test
    void startMatchTestWithUniqueRecords() {
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
            () -> assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startMatch("Mexico", "Canada"),
                () -> scoreboard.startMatch("Spain", "Brazil"))
        );
    }
}