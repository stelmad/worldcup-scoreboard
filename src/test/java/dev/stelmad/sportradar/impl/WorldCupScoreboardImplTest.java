package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WorldCupScoreboardImplTest {

    @Test
    void startMatchTestWithUniqueRecords() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        assertAll(
            () -> assertNotNull(scoreboard.startMatch("Mexico", "Canada")),
            () -> assertNotNull(scoreboard.startMatch("Spain", "Brazil")),
            () -> assertNotNull(scoreboard.startMatch("Germany", "France")),
            () -> assertNotNull(scoreboard.startMatch("Uruguay", "Italy")),
            () -> assertNotNull(scoreboard.startMatch("Argentina", "Australia"))
        );
    }

    @Test
    void startMatchTestWithDuplicates() {
        Scoreboard scoreboard = new WorldCupScoreboardImpl();
        assertAll(
            () -> assertNotNull(scoreboard.startMatch("Mexico", "Canada")),
            () -> assertNotNull(scoreboard.startMatch("Spain", "Brazil")),
            () -> assertThrows(IllegalArgumentException.class,
                () -> scoreboard.startMatch("Mexico", "Canada"),
                () -> scoreboard.startMatch("Spain", "Brazil"))
        );
    }
}