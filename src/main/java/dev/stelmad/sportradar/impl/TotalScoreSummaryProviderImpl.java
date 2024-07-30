package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import dev.stelmad.sportradar.SummaryProvider;
import dev.stelmad.sportradar.model.ScoreboardRecord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of the SummaryProvider interface that provides a summary of the scoreboard
 * sorted by total score.
 */
public class TotalScoreSummaryProviderImpl implements SummaryProvider {

    private final Scoreboard scoreboard;

    /**
     * Constructs a TotalScoreSummaryProviderImpl with the specified scoreboard.
     *
     * @param scoreboard the scoreboard to be used for generating the summary
     * @throws NullPointerException if the scoreboard is null
     */
    public TotalScoreSummaryProviderImpl(Scoreboard scoreboard) {
        this.scoreboard = Objects.requireNonNull(scoreboard);
    }

    /**
     * Returns a summary of the scoreboard records sorted by total score in descending order.
     * If two records have the same total score, they are sorted by their original order.
     *
     * @return a list of scoreboard records sorted by total score in descending order
     */
    @Override
    public List<ScoreboardRecord> getSummary() {
        List<ScoreboardRecord> summary = new ArrayList<>(this.scoreboard.getRecordList());
        summary.sort(
            Comparator.comparingInt(ScoreboardRecord::getTotalScore)
                      .thenComparing(summary::indexOf)
                      .reversed()
        );
        return summary;
    }
}
