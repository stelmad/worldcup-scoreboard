package dev.stelmad.sportradar;

import dev.stelmad.sportradar.model.ScoreboardRecord;

import java.util.List;

public interface SummaryProvider {
    List<ScoreboardRecord> getSummary();
}
