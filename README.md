
<img src="https://i.imgur.com/WWvlw91.png" title="Sketch2MultipleGUIs" alt="Sketch2MultipleGUIs">

# Sketch2MultipleGUIs

Sketch2MultipleGUIs is a project developed to convert hand-drawn mock-ups to multiple types of real GUIs. The target GUI can be generated for web applications, desktop applications using CSharp, or Android applications. It uses deep learning to detect and recognize hand-drawn mockups based on <a href="https://github.com/ultralytics/yolov5" target="_blank">YOLOv5</a>, then it converts the recognized mock-ups to a real app using the postprocessor and  guigenerator modules. The input is just a captured image of the hand-draw mockups and the output is the corresponding working web/csharp/android GUI.

# Project architecture

The architecture of this project is shown in the following figure.

<img src="https://i.imgur.com/ztxVjsL.png" title="System architecture" alt="System architecture">

---

# Hand-drawn mock-up recognition 

```shell
!git clone https://github.com/ultralytics/yolov5  # clone repo
!pip install -qr yolov5/requirements.txt  # install dependencies (ignore errors)
%cd yolov5

import torch
from IPython.display import Image, clear_output  # to display images
from utils.google_utils import gdrive_download  # to download models/datasets

clear_output()
print('Setup complete. Using torch %s %s' % (torch.__version__, torch.cuda.get_device_properties(0) if torch.cuda.is_available() else 'CPU'))
```

```shell
%cat data.yaml
```

```shell
# define number of classes based on YAML
import yaml
with open("data.yaml", 'r') as stream:
    num_classes = str(yaml.safe_load(stream)['nc'])
```

```shell
!gdown --id 1czESPsKbOWZF7_PkCcvRfTiUUJfpx12i -O models/yolov5x.yaml
```

```shell
%%time
#%cd /content/yolov5/
#!python train.py --img 416 --batch 16 --epochs 100 --data '../data.yaml' --cfg ./models/custom_yolov5s.yaml --weights '' --name yolov5s_results --nosave --cache
!python train.py --img 640 --batch 10 --epochs 200 --data 'data.yaml' --cfg models/yolov5x.yaml --weights 'yolov5x.pt' --name yolov5x_results --nosave --cache
```

```shell
%load_ext tensorboard
%tensorboard --logdir runs
```

```shell
rom utils.utils import plot_results; plot_results()  # plot results.txt as results.png
Image(filename='./results.png', width=1000)  # view results.png
```
<img src="https://i.imgur.com/28SDU9A.png" title="Training Results" alt="Training Results">

```shell
print("GROUND TRUTH TRAINING DATA:")
Image(filename='./test_batch0_gt.jpg', width=900)
```

> Model testing for classification

<img src="https://i.imgur.com/t1Z25GU.jpg" title="Testing Results" alt="Testing Results">

```shell
print("GROUND TRUTH AUGMENTED TRAINING DATA:")
Image(filename='./train_batch2.jpg', width=900)
```

<img src="https://i.imgur.com/7AZTXWN.jpg" title="Testing Results" alt="Testing Results">

```shell
!python detect.py --weights weights/last_yolov5x_results.pt --img 640 --conf 0.6 --source zTest/
```

```shell
import glob
from IPython.display import Image, display

for imageName in glob.glob('inference/output/*.jpg'): #assuming JPG
    display(Image(filename=imageName))
    print("\n")
```

<img src="https://i.imgur.com/7qY4auk.jpg" title="Test1" alt="Test1">

<img src="https://i.imgur.com/Rjj0apT.jpg" title="Test2" alt="Test2">

<img src="https://i.imgur.com/NRWGauy.jpg" title="Test3" alt="Test3">

<img src="https://i.imgur.com/34BBoI4.jpg" title="Test4" alt="Test4">

<img src="https://i.imgur.com/s6cAMig.jpg" title="Test5" alt="Test5">

<img src="https://i.imgur.com/zhNcV3h.jpg" title="Test6" alt="Test6">

<img src="https://i.imgur.com/QfZjFr3.jpg" title="Test7" alt="Test7">

<img src="https://i.imgur.com/EiqgZEi.jpg" title="Test8" alt="Test8">

<img src="https://i.imgur.com/hrnGsCE.jpg" title="Test9" alt="Test9">

<img src="https://i.imgur.com/YGAKGAt.jpg" title="Test10" alt="Test10">

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
