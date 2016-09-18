#!/bin/sh

cd src
javac -d . *.java
java -cp .:../lib/mysql-connector-java-5.1.39-bin.jar calendar.StartMenu
cd ..
