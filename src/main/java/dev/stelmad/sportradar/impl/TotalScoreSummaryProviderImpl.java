package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import dev.stelmad.sportradar.SummaryProvider;
import dev.stelmad.sportradar.model.ScoreboardRecord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class TotalScoreSummaryProviderImpl implements SummaryProvider {

    private final Scoreboard scoreboard;

    public TotalScoreSummaryProviderImpl(Scoreboard scoreboard) {
        this.scoreboard = Objects.requireNonNull(scoreboard);
    }

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
