# Sketch Builder (code generator part)

Sketch Builder is an application that use AI and computer Vision technologies to convert sketch design to real code in multiple programming languages, the code generator part is the part which is responsible for the final output code of the chosen language.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- jdk 8
- gradle build tool
- set JAVA_HOME environment variable to your jdk path

### Installing


1- Using gradle:
- clone the project to your device and unzip it
- open the terminal in the main directory
- write the following command:

*on Linux
```
$ sh gradlew build
```

*on windows
```
$ gradlew build
```
- you will find the jar inside "build/libs" directory

2- Using IDE:
- clone the project to your device and unzip it.
- open the project in Intellij IDE.
- execute a jar file for the application.
- open the terminal in the directory of the executed jar file.
- you are now ready to use the application.

### How to use

```
$ ./java -jar generator.jar [INPUT_TYPE] [INPUT_PATH] [OUTPUT_PATH]
```

1- [INPUT_TYPE]: => there are three parameters --android, --html and --csharp
    > use --android for ANDROID output code type
	> use --html for HTML output code type
	> use --csharp for C# output code type
	
2- [INPUT_PATH]: => you can enter a directory path containing the json files or multiple json files paths separated by space or just a single json file path

3- [OUTPUT_PATH]: => you must insert an existing folder path for the output

### Example

-lets suppose we have file "/home/test/a.json" and we want android output for this file

```
$ ./java -jar generator.jar --andriod /home/test/a.json CODE
```

## After running this command
	- you will find that the output directory contains "app" directory that includes the output.

## Main Project:
- [Sketch builder] (https://github.com/zezomousa101/Sketch-Builder)
	





