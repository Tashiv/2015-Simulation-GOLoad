# 2015-Simulation-GOLoad
A tool for testing thread performance of hardware using a game of life game. This is useful for testing system stability and cooling peformance in everything from a Windows desktop to the Raspberry Pi Zero.

## Compile
```bash
# compile java files
javac com/tashiv/goload/*.java

# package as runnable jar file
jar cfm goload.jar manifest/MANIFEST.MF com/tashiv/goload/*.class
```

## Usage
```bash
# run the jar file
java -jar goload.jar
```

## Screenshots
<img src="https://github.com/Tashiv/2015-Simulation-GOLoad/blob/master/.media/running.gif?raw=true">
