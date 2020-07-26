# Sketch2MultipleGUIs

Sketch2MultipleGUIs is a project developed to convert hand-drawn mock-ups to multiple types of real GUIs. The target GUI can be generated for web applications, desktop applications using CSharp, or Android applications. It uses deep learning to detect and recognize hand-drawn mockups based on <a href="https://github.com/ultralytics/yolov5" target="_blank">YOLOv5</a>, then it converts the recognized mock-ups to a real app using the postprocessor and  guigenerator modules. The input is just a captured image of the hand-draw mockups and the output is the corresponding working web/csharp/android GUI.

# Project architecture

The architecture of this project is shown in the following figure.

<img src="https://i.imgur.com/ztxVjsL.png" title="System architecture" alt="System architecture">

# Hand-drawn mock-up recognition 
<img src="https://i.imgur.com/28SDU9A.png" title="Training Results" alt="Training Results">
<img src="https://i.imgur.com/A1zPvWz.png" title="Validation Results" alt="Validation Results">

# Postprocessing
The postprocessing part is responsible for reading the output files of the recognition part and converts it to json file depending on chosen target language.

> Prerequisites

gcc compiler(build version 8.3.0)
cmake build tool
Linux System (ubuntu, debian are tested)
boost library

# GUI Generation

# Model testing for classification
<img src="https://i.imgur.com/t1Z25GU.jpg" title="Testing Results" alt="Testing Results">

# Postprocessing

# GUI generation

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

***INSERT GRAPHIC HERE (include hyperlink in image)***


# Repository Title Goes Here

> Subtitle or Short Description Goes Here

> ideally one sentence

> include terms/tags that can be searched

**Badges will go here**

- build status
- issues (waffle.io maybe)
- devDependencies
- npm package
- coverage
- slack
- downloads
- gitter chat
- license
- etc.

***INSERT ANOTHER GRAPHIC HERE***

- Most people will glance at your `README`, *maybe* star it, and leave
- Ergo, people should understand instantly what your project is about based on your repo

> Tips

- HAVE WHITE SPACE
- MAKE IT PRETTY
- GIFS ARE REALLY COOL

> GIF Tools

## Table of Contents (Optional)

> If your `README` has a lot of info, section headers might be nice.

- [Installation](#installation)
- [Features](#features)
- [Contributing](#contributing)
- [Team](#team)
- [FAQ](#faq)
- [Support](#support)
- [License](#license)


---

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

---

## Installation

- All the `code` required to get started
- Images of what it should look like

### Clone

- Clone this repo to your local machine using `https://github.com/fvcproductions/SOMEREPO`

### Setup

- If you want more syntax highlighting, format your code like this:

> update and install this package first

```shell
$ brew update
$ brew install fvcproductions
```

> now install npm and bower packages

```shell
$ npm install
$ bower install
```

- For all the possible languages that support syntax highlithing on GitHub (which is basically all of them), refer <a href="https://github.com/github/linguist/blob/master/lib/linguist/languages.yml" target="_blank">here</a>.

---

## Features
## Usage (Optional)
## Documentation (Optional)
## Tests (Optional)

- Going into more detail on code and technologies used
- I utilized this nifty <a href="https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet" target="_blank">Markdown Cheatsheet</a> for this sample `README`.

---

## Contributing

> To get started...

### Step 1

- **Option 1**
    - 🍴 Fork this repo!

- **Option 2**
    - 👯 Clone this repo to your local machine using `https://github.com/joanaz/HireDot2.git`

### Step 2

- **HACK AWAY!** 🔨🔨🔨

### Step 3

- 🔃 Create a new pull request using <a href="https://github.com/joanaz/HireDot2/compare/" target="_blank">`https://github.com/joanaz/HireDot2/compare/`</a>.

---
