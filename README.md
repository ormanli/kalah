# kalah

Example [kalah](https://en.wikipedia.org/wiki/Kalah) game implementation using Micronaut, RuleBook and Quasar Actors.

Info
====
* When the application receives a request to create a new game, it spawns a new `GameActor`. After that for each game-related action is routed to corresponding `GameActor`.
* There is no persistence and error handling.
* RuleBook used to implement rules as the chain of responsibility under `com.serdarormanli.kalah.rules` package.
* Default number of pits per player and number of stones per pit is configurable. They can be overridden in `application.yaml`.
* Rest api served at `localhost:8080/kalah` by default.
* Rest api has 3 resources.
* `/kalah POST` creates new game returns board. Takes `numberOfPitsPerPlayer` and `numberOfStonesPerPit` as optional query parameters.
* `/kalah/{gameId} GET` returns latest snapshot of game.
* `/kalah/{gameId}/indexOfPit/{indexOfPit} POST` picks stones and distributes. Returns latest snapshot of the game.
* There is a basic frontend served at `localhost:8080` by default.

Used Technologies
====
* Java 11
* Micronaut
* Quasar
* RuleBook

⚠️ This application requires Java 11 for build and run. Newer Java versions are not supported. ⚠️