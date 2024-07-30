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
- Each match is going to be assigned with an ID value in the format: "HOMETEAMvsAWAYTEAM".
- A match ID is returned once a new match is started.
- A match ID should be provided to finish the match.
- A match ID should be provided to update the match.
- Negative score values are considered to be invalid for update operations.

## Local build and installation
1. Clone the repository
2. Build and install into your local Maven repository `mvn clean install`
3. Add as a dependency into your project 
```xml
<dependency>
   <groupId>dev.stelmad.sportradar</groupId>
   <artifactId>football-scoreboard-library</artifactId>
   <version>1.0.0-SNAPSHOT</version>
</dependency>
```

## How to use
Refer to `dev.stelmad.sportradar.WorldCupScoreboardIT` to get more details on usage.
