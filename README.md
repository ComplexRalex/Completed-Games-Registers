# Completed-Games Registers

<p align="center">
    <img src="https://raw.githubusercontent.com/ComplexRalex/Completed-Games-Registers/master/res/gui/logotype.png" title="Logotype of Completed-Games Register software" alt="Completed-Games Registers">
</p>

<p align="center">
    <a href="https://github.com/ComplexRalex/Completed-Games-Registers/releases/latest"><img src="https://img.shields.io/github/v/release/ComplexRalex/Completed-Games-Registers" alt="GitHub release (latest by date)"/></a>
    <a href="https://github.com/ComplexRalex/Completed-Games-Registers/releases/latest"><img src="https://img.shields.io/github/release-date/ComplexRalex/Completed-Games-Registers" alt="GitHub Release Date"/></a>
    <a href="https://github.com/ComplexRalex/Completed-Games-Registers/commit/master"><img src="https://img.shields.io/github/last-commit/ComplexRalex/Completed-Games-Registers" alt="GitHub last commit"/></a>
    <a href="https://github.com/ComplexRalex/Completed-Games-Registers/blob/master/LICENSE"><img src="https://img.shields.io/github/license/ComplexRalex/Completed-Games-Registers" alt="GitHub"/></a>
</p>

There were some changes across versions of this software. I encourage you to check out the details [here](#important-notes-about-updates). In case you want to download a _release_ version, go [here](#about-release-versions).

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

Also, you can see additional information about the game like _its description_, _its publishers and developers_, _its genres_, and even an _image or screenshot_!
These things are thanks to [RAWG's database API](https://rawg.io/apidocs).

**Note:** When you are downloading game information, it will take a few seconds to process it. *Be patient*.

## Requirements

* This program requires _at least_ **Java 1.8** to be executed (in fact, is recommended to run it at that version). You can download it [here](https://java.com/en/download/).
* This program uses the **Open Sans** font by default, but _it isn't necessary_ to run it. Anyways, if you want to download it, click [here](https://fonts.google.com/specimen/Open+Sans).

## Important notes about updates

### [[v1.1.1](https://github.com/ComplexRalex/Completed-Games-Registers/releases/tag/v1.1.0)] Versions from _1.1.1_ requires ``lib`` folder

Because of a change made in the construction of jar files, it's necessary to make a ``lib`` folder and include all the libraries inside of them in order to run the program correctly.

If you want to update from older versions to this one (or above), you can simply copy the contents of the new release into the folder of the previous jar version (replacing the old jar file and including the ``lib`` folder contents).

In case this is the first version you downloaded, you can ignore this and simply extract the contents of the release version.

### [[v1.0.3](https://github.com/ComplexRalex/Completed-Games-Registers/releases/tag/v1.0.3)] RAWG Database now requires an API key in order to accept requests

This problem is fixed in the 1.0.3 version. However, this implies that previous versions will not allow downloading game information from RAWG Database.

### [[v1.0.0](https://github.com/ComplexRalex/Completed-Games-Registers/releases/tag/v1.0.0)] Downloaded game information

Most of the game information files (``json``, inside ``data/game/json`` folder), which can be downloaded from the creation/editing menu of a **completed-game _register_**, won't be accessible if you downloaded them in version 1.0.0 and want to access them **in future versions**. This is because of a modification made in the name of the files, doing it more compatible with more special characters.

If you are currently using the **1.0.0 version** and you downloaded game information (only ``json`` files) but you want to update your JAR file (or update from git), it's recommendable to _delete_ and re-download these files.

**Note:** This won't affect the game registers that you have created. **This is related to the ``json`` files which are inside the ``data/game/json`` folder.**

Sorry for the inconvenience.

## About *release* versions

Everything you need to know about ``jar`` file releases can be consulted [here](https://github.com/ComplexRalex/Completed-Games-Registers/releases).

## How to build from source files and run

All the ``run`` files are inside the ``run`` folder. Be sure to run them **at that same location** (that location is ``run/<system-you-selected>``).

* In *Windows*, go to ``run/windows``, execute ``build-class.bat``, and then execute ``run.bat``.
* In *Linux*, go to ``run/linux``, execute ``build-class.sh``, and then execute ``run.sh``.

**Note 1:** After running the ``build-class`` file, every time you want to run the program *you only need to execute the ``run`` file.*

**Note 2:** From now on, you must **get a new RAWG API key** in order to download information from their database. This can be obtained [here](https://rawg.io/login?forward=developer). The location where you need to put it in is on the API_KEY String on the **Software** java class (that is ``src/system/Software.java``).

```java
    /**
     * RAWG API key.
     */
    public static final String API_KEY = "INSERT-YOUR-RAWG-API-KEY-HERE";
```

**Note 3:** There are more runnables you can execute, so here's a brief explanation about them.

* ``build`` will generate ``class`` files and finally a ``jar`` file.
* ``build-and-run`` will generate ``class`` files and run the program from them.
* ``build-class`` will only generate ``class`` files. These files will be stored in the ``build`` folder.
* ``build-jar`` will only generate a ``jar`` file (which requires ``class`` files). The ``jar`` file will be stored in the path ``run/<system-you-selected>/jar``, containing the ``data`` and ``lib`` folders as well. In case you want to run the jar file, you can do it with ``java -jar CGR.jar``.
* ``run`` will only run the program (which won't work if there were no ``class`` files found). This will store all the program data in the path ``run/<system-you-selected>/run/data``.

## Screenshots

![Completed-Games Registers screenshot 1](https://i.imgur.com/vpPljiA.png "screenshot 1")
![Completed-Games Registers screenshot 2](https://i.imgur.com/o2P3vJv.png "screenshot 2")
![Completed-Games Registers screenshot 3](https://i.imgur.com/64DRt3m.png "screenshot 3")
![Completed-Games Registers screenshot 4](https://i.imgur.com/HCWn506.png "screenshot 4")

## Report issues!

This software is not perfect. Exists the possibility to find a bug that could ruin the user experience, so if you found a bug, I encourage you to [report it](https://github.com/ComplexRalex/Completed-Games-Registers/issues/new/choose)! (you can even suggest a new feature!).

## Disclaimer

All the icons and images that are used in this program (except for those which can be downloaded through [RAWG's database API](https://rawg.io/apidocs)) are entirely made by me, from zero.
I don't own any of the games that are mentioned on the screenshots.
