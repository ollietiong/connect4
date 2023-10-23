# connect4

This is a Connect 4 Android app. 

Built using Android studio, it uses Java as a language to build the back end and provide the functionality for rendering the XML files. 

It can be played as 2 player or 1 player versus a simple “Computer Player”. 

The goal of the game is to place counters into the board to try and get four in a row, either
vertically, horizontally or diagonally. This is done by tapping on the column you wish to drop your counter into when it is your turn. The screen indicates whose turn it is, and will display a message when someone has won.

After an initial intro animation screen, there is a basic menu to select number of players, you are then taken to the game board.

There is a basic “computer player”, that should not be too hard to beat, but may pull a few surprises. Put this way it does have some idea about what you may be up to, as it is calculating the current longest connection that you have developed on the board and will try to block that (to a certain extent).

Its not quite the most intelligent computer player, but it is an initial step up from dropping at random.

The repository includes the build files produced by the Android Studio application, the source code includes 12 files containing the various components of the game. There are also all of the various resources used such as the xml files which help build the look of the app.

The game is split into the following components; the board, board connections, a cell (presenting a single  cell on the board), a human and computer player, various gameplays and the user interface : main and menu activities, game activity.

These were all developed independentally with OOP principles in mind.
