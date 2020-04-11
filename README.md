# Dungeon Disaster
#### By **Michael Potemkin, Kirill Larshin, Jeremy Kimotho, Martin Wong**
#### 29-March-2020
#### Repository: https://github.com/HerbalCoffee/Our-Project-CPSC
## Description
A rogue-like adventure game. The player is able to explore randomly generated maps, collect items and fight enemies in order to battle a final boss.
## Setup/Installation Requirements
* Java 8 or later
* JavaFX (For running the GUI version)
* All testing libraries are provided in this respository for your convenience
## How to build/run (Text-Based)
1. Ensure that a Java JVM is installed on your machine (run Java -version from the terminal)
2. Download/Clone this repository
3. Open the terminal and navigate to the downloaded directory
4. Navigate to DungeonDisaster/src/models
5. Run javac *.java inside this directory
6. Navigate to DungeonDisaster directory
7. Run javac -cp src src/text/*.java inside this directory
8. Run java -cp src text.TextApp from inside this directory
9. Enjoy!
## How to build/run (GUI-Based)
1. Ensure that a Java JVM is installed on your machine (run Java -version from the terminal)
2. Download/Clone this repository
3. Open the terminal and navigate to the downloaded directory
4. Navigate to DungeonDisaster/src/models
5. Run javac *.java inside this directory
6. Navigate to DungeonDisaster directory
7. Run javac -cp src src/graphic/*.java inside this directory
8. Run java -cp src graphic.GUIApplication from inside this directory
9. Enjoy!
## How to test (JUnit)
1. Ensure that a Java JVM is installed on your machine (run Java -version from the terminal)
2. Download/Clone this repository
3. Open the terminal and navigate to the downloaded directory
4. Navigate to DungeonDisaster directory
5. Run javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar src/models/*.java inside this directory
6. Run javac -cp src:junit-4.12.jar:hamcrest-core-1.3.jar test/*.java inside this directory
7. Run java -cp test:src:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore MovableEntityTest from inside this directory to run the test
8. JUnit should report "OK" if all tests passed successfully

MIT License

Copyright (c) 2020 Michael Potemkin, Kirill Larshin, Martin Wong, Jeremy Kimotho

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
