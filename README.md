# Football World Cup Scoreboard Library

## Requirements
1. **Start a new match**, assuming initial score 0 – 0 and adding it the scoreboard. This should capture following parameters:
   - Home team
   - Away team
1. **Update score**. This should receive a pair of absolute scores:
   - home team score
   - away team score
1. **Finish match** currently in progress. This removes a match from the scoreboard.
1. **Get a summary** of matches in progress ordered by their total score. The matches with the
   same total score will be returned ordered by the most recently started match in the
   scoreboard.

## Assumptions
- One team can play one game at a time.
- A unique match ID is returned once a new match is started.
