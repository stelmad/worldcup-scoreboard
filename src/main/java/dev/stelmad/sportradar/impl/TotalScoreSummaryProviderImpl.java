package dev.stelmad.sportradar.impl;

import dev.stelmad.sportradar.Scoreboard;
import dev.stelmad.sportradar.SummaryProvider;
import dev.stelmad.sportradar.model.ScoreboardRecord;

import java.util.List;
import java.util.Objects;

public class TotalScoreSummaryProviderImpl implements SummaryProvider {

    private final Scoreboard scoreboard;

    public TotalScoreSummaryProviderImpl(Scoreboard scoreboard) {
        this.scoreboard = Objects.requireNonNull(scoreboard);
    }

    @Override
    public List<ScoreboardRecord> getSummary() {
        throw new UnsupportedOperationException();
    }
}
