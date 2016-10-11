#!/bin/sh

cd src
javac -d . *.java
java -cp calendar.StartMenu
cd ..
