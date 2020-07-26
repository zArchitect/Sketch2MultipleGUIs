# Sketch2MultipleGUIs

Sketch2MultipleGUIs is a project developed to convert hand-drawn mock-ups to multiple types of real GUIs. The target GUI can be generated for web applications, desktop applications using CSharp, or Android applications. It uses deep learning to detect and recognize hand-drawn mockups based on <a href="https://github.com/ultralytics/yolov5" target="_blank">YOLOv5</a>, then it converts the recognized mock-ups to a real app using the postprocessor and  guigenerator modules. The input is just a captured image of the hand-draw mockups and the output is the corresponding working web/csharp/android GUI.

# Project architecture

The architecture of this project is shown in the following figure.

<img src="https://i.imgur.com/ztxVjsL.png" title="System architecture" alt="System architecture">

---

# Hand-drawn mock-up recognition 

***INSERT GRAPHIC HERE (include hyperlink in image)***

**Badges will go here**

<img src="https://i.imgur.com/28SDU9A.png" title="Training Results" alt="Training Results">

<img src="https://i.imgur.com/A1zPvWz.png" title="Validation Results" alt="Validation Results">

### Step 1

- **Option 1**
    - 🍴 Fork this repo!

- **Option 2**
    - 👯 Clone this repo to your local machine using `https://github.com/joanaz/HireDot2.git`

> Example

```javascript
// code away!

let generateProject = project => {
  let code = [];
  for (let js = 0; js < project.length; js++) {
    code.push(js);
  }
};
```

> Model testing for classification

<img src="https://i.imgur.com/t1Z25GU.jpg" title="Testing Results" alt="Testing Results">

---

# Postprocessing

The postprocessing part is responsible for reading the output files of the recognition part and converts it to json file depending on chosen target language.

> Prerequisites

- gcc compiler(build version 8.3.0)
- cmake build tool
- Linux System (ubuntu, debian are tested)
- boost library

> Installation

1- clone the project to your device and unzip it.

2- open the compiler directory suppose it called POSTPROCESSOR and create directory called build then open it in the terminal.

```shell
$ cd POSTPROCESSOR
$ mkdir build
$ cd build
```

3- compile the project and create the executable file

```shell
$ cmake ..
$ make
```

4- open the executable file directory

```shell
$ cd ..
$ cd bin
```
> Example

- Lets suppose we have file "/home/Downloads/test.viw" and want to convert it to android json file

```shell
$ ./postprocessor --android /home/Downloads/test.viw
```

---

# GUI generation

> Prerequisites

- dk 8
- gradle build tool
- set JAVA_HOME environment variable to your jdk path

> Installation

1- Using gradle:

- clone the project to your device and unzip it
- open the terminal in the main directory
- write the following command:

*on Linux

```shell
$ sh gradlew build
```

*on windows

```shell
$ gradlew build
```

- you will find the jar inside "build/libs" directory

2- Using IDE:

- clone the project to your device and unzip it.
- open the project in Intellij IDE.
- execute a jar file for the application.
- open the terminal in the directory of the executed jar file.
- you are now ready to use the application.

> How to use

```shell
$ ./java -jar generator.jar [INPUT_TYPE] [INPUT_PATH] [OUTPUT_PATH]
```

1- [INPUT_TYPE]: => there are three parameters --android, --html and --csharp > use --android for ANDROID output code type > use --html for HTML output code type > use --csharp for C# output code type

2- [INPUT_PATH]: => you can enter a directory path containing the json files or multiple json files paths separated by space or just a single json file path

3- [OUTPUT_PATH]: => you must insert an existing folder path for the output

> Example

- lets suppose we have file "/home/test/app.json" and we want android output for this file

```shell
$ ./java -jar generator.jar --andriod /home/test/app.json CODE
```

---
