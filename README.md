# Youssef Mohamed Abdelhamid Mohamed, 20398219

The code can be compiled but just adding the files into a project in Eclipse or Intellij, pointing it towards the main function in src/main/java/com/example/demo/game/Main or com.example.demo.game.Main.

Javadocs are in "javadoc" folder, in the root folder.

Implemented features:
-Randomized Colour themes.
-Highscore saving.
-Retry button.
-2048 Game Engine.

Non-implemented features:

Unit tests. My mistake, starting working on refactoring before unit tests, made it too difficult to start working on them.

Build files. Too difficult.

Groundbreaking idea. No ideas on what to add, maybe the randomized colour theme could count.

New classes:

Movement class. It was made to contain all the movement functions in GameScene.

Colours class. It was made to contain a hashmap for all the Colours, as well as randomize them to generate colour themes.

Modified classes:
Cell. It was modified to call the colours from the Colours class, instead of using a large switch statement.
EndGame, It was modified to call the Accounts class to check for a new highscore.
Main, It was modified to initalize the colours, and call the main_menu function in GameScene.
GameScene, It was modified to make a new main_menu() function, which takes username, starts the game, and can change the colour theme using the Colours class.
Account, It was modified to now contain one method which loops through highscore.txt to check for new user, new highscore, or if the user is a Guest.
