# EcoBochos
Educational Trivia App about the environment, made for the Renault Foundation. Fun game of questions and answers made to raise awareness on environmental issues, aimed at children and teenagers.

## Introduction
EcoBochos is a fun game of questions and answers geared towards learning environmental facts on five important subjects: Energy, Mobility, Biodiversity, Water and Waste. The questions were designed by experts in environmental education and have varying levels of difficulty. The game was programmed with the LibGDX framework, and is thus available in Android, iOS, Desktop and HTML versions. The game can be run easily on desktop by downloading and running the latest jar: EcoCuis-Desktop-v22.jar. The app is also available on the Play Store

## Folder structure
The project is divided into 5 main folders: ios, android, desktop, html and core. The first four have specific porting code to make the game run on the respective platform, and core has the main classes and methods that define the inner workings of the program. Look into *core* if searching for the actual code structure.

## Code
The code is fully modularized and adheres to OOP concepts. The LibGDX framework allows for an easy abstraction from complex rendering situations. The main game loop is handled by the EQGame class, that extends a libGDX Game and manages allthe important actors of the program. When a match is to be played, the class EQMatch handles the basic actions required, and is extended by EQNormalMatch and EQTimeMatch for gamemode specific behavior. The code follows the Actor - Stage paradigm from LibGDX< that allows for easy rendering, and Widget management, as well as straightforward compounded translations, rotations and scalings triggered by events.

## Game screenshots
![EcoBochos main menu](https://github.com/cfosco/EcoBochos/blob/master/Images%20and%20auxiliar%20files/Screenshot%201.png)
![EcoBochos match](https://github.com/cfosco/EcoBochos/blob/master/Images%20and%20auxiliar%20files/Screenshot%203.png)
