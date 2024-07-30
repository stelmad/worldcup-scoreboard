package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import dev.stelmad.sportradar.SummaryProvider;
import dev.stelmad.sportradar.model.ScoreboardRecord;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class TotalScoreSummaryProviderImplTest {
    @Test
    void getSummaryTest() {
        List<ScoreboardRecord> recordsList = List.of(
            buildScoreRecord("Mexico", 0, "Canada", 5),
            buildScoreRecord("Spain", 10, "Brazil", 2),
            buildScoreRecord("Germany", 2, "France", 2),
            buildScoreRecord("Uruguay", 6, "Italy", 6),
            buildScoreRecord("Argentina", 3, "Australia", 1)
        );

        Scoreboard scoreboardMock = mock(Scoreboard.class);
        when(scoreboardMock.getRecordList()).thenReturn(recordsList);

        SummaryProvider worldCupSummaryProvider = new TotalScoreSummaryProviderImpl(scoreboardMock);

        List<ScoreboardRecord> summaryList = worldCupSummaryProvider.getSummary();
        assertAll(
            () -> assertNotNull(summaryList),
            () -> assertEquals(recordsList.size(), summaryList.size()),
            () -> {
                ScoreboardRecord record = summaryList.get(0);
                assertNotNull(record);
                assertEquals("Uruguay", record.getHomeTeamName());
                assertEquals("Italy", record.getAwayTeamName());
                assertEquals(6, record.getHomeTeamScore());
                assertEquals(6, record.getAwayTeamScore());
            },
            () -> {
                ScoreboardRecord record = summaryList.get(1);
                assertNotNull(record);
                assertEquals("Spain", record.getHomeTeamName());
                assertEquals("Brazil", record.getAwayTeamName());
                assertEquals(10, record.getHomeTeamScore());
                assertEquals(2, record.getAwayTeamScore());
            },
            () -> {
                ScoreboardRecord record = summaryList.get(2);
                assertNotNull(record);
                assertEquals("Mexico", record.getHomeTeamName());
                assertEquals("Canada", record.getAwayTeamName());
                assertEquals(0, record.getHomeTeamScore());
                assertEquals(5, record.getAwayTeamScore());
            },
            () -> {
                ScoreboardRecord record = summaryList.get(3);
                assertNotNull(record);
                assertEquals("Argentina", record.getHomeTeamName());
                assertEquals("Australia", record.getAwayTeamName());
                assertEquals(3, record.getHomeTeamScore());
                assertEquals(1, record.getAwayTeamScore());
            },
            () -> {
                ScoreboardRecord record = summaryList.get(4);
                assertNotNull(record);
                assertEquals("Germany", record.getHomeTeamName());
                assertEquals("France", record.getAwayTeamName());
                assertEquals(2, record.getHomeTeamScore());
                assertEquals(2, record.getAwayTeamScore());
            }
        );
    }

    @Test
    void getSummaryTestInvalidArgs() {
        assertThrows(NullPointerException.class, () -> new TotalScoreSummaryProviderImpl(null));
    }

    @Test
    void getSummaryTestEmptyScoreboard() {
        Scoreboard scoreboardMock = mock(Scoreboard.class);
        when(scoreboardMock.getRecordList()).thenReturn(Collections.emptyList());

        SummaryProvider worldCupSummaryProvider = new TotalScoreSummaryProviderImpl(scoreboardMock);

        List<ScoreboardRecord> summaryList = worldCupSummaryProvider.getSummary();
        assertAll(
            () -> assertNotNull(summaryList),
            () -> assertTrue(summaryList.isEmpty())
        );
    }

    private ScoreboardRecord buildScoreRecord(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) {
        ScoreboardRecord record = new ScoreboardRecord(homeTeamName, awayTeamName);
        record.setHomeTeamScore(homeTeamScore);
        record.setAwayTeamScore(awayTeamScore);
        return record;
    }
}