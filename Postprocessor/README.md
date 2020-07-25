# Sketch Builder (equalizer part)

sketch builder is an application that use AI and computer Vision technologies to convert design sketch to real code in multiple programming languages, the equalizer part is the part which is responsible for read the viw files and convert it to json file depending on chosen language.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes:

### Prerequisites

- gcc compiler(build version 8.3.0)
- cmake build tool
- Linux System (ubuntu, debian are tested)
- boost library


### Installing

1- clone the project to your device and unzip it.

2- open the compiler directory suppose it called EQUALIZER_HOME and create directory called build then open it in the terminal.

```
$ cd EQUALIZER_HOME
$ mkdir build
$ cd build
```

3- compile the project and create the executable file

```
$ cmake ..
$ make
```

4- open the executable file directory

```
$ cd ..
$ cd bin
```


## How to use the application

```
$ ./compiler [LANGUAGE][INIPUT_PATH]
```

1-[INPUT_TYPE] (required) :=> choose your programming language that you wnat [--android, --html, --csharp]

2-[INPUT_PATH] (required):=> you can enter multiple .viw paths separated by space or path to directory the containe multiple .viw files or just single .viw file.


### Example

- lets suppose we have file "/home/Downloads/test.viw" and want to convert it to android json file

```
$ ./equalizer --android /home/Downloads/test.viw
```

## After running this command:
- you will find json dir include readable file called test.json containe the detected objects inforamtion as a json format


## Main Project:
- [Sketch builder] (https://github.com/zezomousa101/Sketch-Builder)