# Completed-Games Registers
![Logotype of the program 'Completed-Games Registers'](https://raw.githubusercontent.com/ComplexRalex/Completed-Games-Registers/master/res/gui/logotype.png "Logotype of the program.")

If you are using a **release** of this program (``.jar`` file), please [read this section](#about-release-jar-files).

## Description
Software (made in **Java**) where you can have a record of every game you have beaten (completed) so far!

## Aim
If you want to save a list with all the games that you have completed so far, you can create a **completed-game _register_**. This one is an object that has the following fields:

* Name of the game.
* The year when you completed it.
* Your rating of the game (from 1 to 5).
* Some comments or thoughts about it.
* Your annotations, like something you must consider before playing it.
* A spoiler (at the *visualization* panel, it won't be visible at first).

The only one of the above mentioned that isn't optional is the first field: the **title of the game**.

Also, you can see additional information about the game like _its description_, _their publishers and developers_, _its genres_, and even an _image or screenshot_!
These things are thanks to the [RAWG's database API](https://rawg.io/apidocs).

## Notes & Advices
* This program requires _at least_ **Java 1.8** to be executed (in fact, is recommended to run it at that version). You can download it [here](https://java.com/en/download/).
* This program uses the **Open Sans** font by default, but it isn't necessary to run it. Anyways, if you want to download it, click [here](https://fonts.google.com/specimen/Open+Sans).
* When you are downloading game information, it will take a few seconds to process it (be patient).

## About "_release JAR_" files
It's recommended to save that JAR file into an empty folder. This is because this program **creates a new folder** named ``data``, where all the files are going to be saved and modified.

### How can I update my JAR file?
You just need to download the JAR file and put it in the same location as the previous one. In fact, you can delete the old one and let there the new one. **Note:** Please read the notes provided in the _release_ versions, so you can finally decide to update or to stay in the same version.

### [WARNING] If you want to update to a new version from 1.0.0, please read this!
Most of the game information files (``.json``, inside ``data/game/json`` folder), which can be downloaded from the creation/editing menu of a **completed-game _register_**, won't be accessible if you got them in the version 1.0.0 and want to access them in future versions. This is because of a modification made in the name of the files, doing it more compatible with more special characters.

If you are currently using the 1.0.0 version and you downloaded game information (only ``.json`` files) but you want to update you JAR file, it's recommendable to _delete_ and re-download these files.

**Note:** This won't affect the game register that you have created. This is related to the ``.json`` files which are inside the ``data/game/json`` folder.

Sorry for the inconvenience.

## How to run
All the ``run`` files are inside of the ``bin`` folder. Be sure to run them **at that same location**.

* In *Windows*, click and execute the ``run.bat`` file (or type ``.\run.bat``).
* In *Linux*, click and execute the ``run.sh`` file (or type ``sh run.sh``).

## Screenshots
![Completed-Games Registers screenshot 1](https://i.imgur.com/l1GdgwX.png "screenshot 1")
![Completed-Games Registers screenshot 2](https://i.imgur.com/Gfcct3x.png "screenshot 2")
![Completed-Games Registers screenshot 3](https://i.imgur.com/7yJZlRr.png "screenshot 3")
![Completed-Games Registers screenshot 4](https://i.imgur.com/cHPfpFu.png "screenshot 4")

## Report issues!
This software is not perfect. Exists the possibility to find a bug that could ruin the user experience, so if you found a bug, I encourage you to [report it](https://github.com/ComplexRalex/Completed-Games-Registers/issues/new/choose)! (you can even suggest a new feature!).

## Disclaimer
All the icons and images that are used in this program (except for those which can be downloaded through [RAWG's database API](https://rawg.io/apidocs)) are entirely made by me, from zero.
I don't own any of the games that are mentioned on the screenshots.
