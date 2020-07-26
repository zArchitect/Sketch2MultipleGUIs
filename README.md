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

## Example (Optional)

```javascript
// code away!

let generateProject = project => {
  let code = [];
  for (let js = 0; js < project.length; js++) {
    code.push(js);
  }
};
```

## Model testing for classification

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

---

# Running demo
```javascript
// code away!

let generateProject = project => {
  let code = [];
  for (let js = 0; js < project.length; js++) {
    code.push(js);
  }
};
```
---
