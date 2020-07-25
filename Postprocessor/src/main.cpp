
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <sys/types.h>
#include <sys/stat.h>
#include <map>
#include <string>

#include "equalizer/Element.hpp"
#include "equalizer/AndroidView.hpp"
#include "equalizer/Files.h"
#include "equalizer/ObjectsTreeController.hpp"
#include "equalizer/AndroidViewsWriter.hpp"
#include "equalizer/HtmlWriter.hpp"
#include "equalizer/CSharpWriter.hpp"

const std::string OUTPUT_DIR = "json";
const int scaller = 5;
enum FLAGES{ANDROID = 1, HTML, CSHARP};


void wirteViews(std::vector<AndroidView> &views, std::string filename);
int round(int perc, int maxScal);
int readElementsFile(std::string flag, std::string file);
int createOutputFile(std::string flag, std::string outputPath, std::vector<Element> &elemnts, int w, int h);




std::map<std::string, int> FlagesParser = {
	{"--android", FLAGES::ANDROID},
	{"--html", FLAGES::HTML},
	{"--csharp", FLAGES::CSHARP}
};


int main(int argc, char const *argv[])
{
	std::cout << "start equalizing:" << std::endl;
	std::cout << "--------------------" << std::endl;
	int exitResult = 1;

	if(argc > 2){
		if(!(FlagesParser[std::string(argv[1])] > 0)){
			std::cout << argv[1] << " is not valid flage" << std::endl;
			return exitResult;
		}
		Files::initializeOutputDires(OUTPUT_DIR);
		for(int i =2; i<argc ; ++i){
			std::string file = std::string(argv[i]);
			
			if(Files::is_file(file.c_str())){
				exitResult = readElementsFile(std::string(argv[1]), file);
			}else{
				std::vector<std::string> files;
				Files::getDirFiles(file, ".viw", files);
				for(size_t j=0; j< files.size();++j){
					exitResult = readElementsFile(std::string(argv[1]), file + Files::slash() + files[j]);
				}
			}

		}
	}else{
		std::cout << "invalid number of arguments" <<std::endl;
	}

	std::cout << "end equalizing:" << std::endl;
	std::cout << "--------------------" << std::endl;
	return exitResult;	
}


int readElementsFile(std::string flag, std::string file){
	std::string filename = Files::getFileName(file) + ".json";
	std::cout << "Processing file: " << file <<std::endl;
	std::vector<Element> elmentsVec;
	std::fstream input(file);
	int width, height;
	
	input >> width;
	input >> height;

	std::string view, id, temp;
	int left, top, right, bottom;

	while(!input.eof()){
		input >> view;
		
		if(input.peek() == EOF)
			break;
		input >> id;
		input >> left;
		input >> top;
		input >> right;
		input >> bottom;
		input >> temp;
		Element e(view, id,left, top, right, bottom);
		elmentsVec.push_back(e);
	}
	input.close();
	return createOutputFile(flag, OUTPUT_DIR + Files::slash() + filename, elmentsVec, width, height);
}

int createOutputFile(std::string flag, std::string outputPath, std::vector<Element> &elemnts, int w, int h){
	ObjectsTreeController* objectsTree;
	switch (FlagesParser[flag])
	{
	case FLAGES::ANDROID:
			objectsTree = new AndroidViewsWriter(elemnts, w, h);
		break;
	case FLAGES::HTML:
		objectsTree = new HtmlWriter(elemnts, w, h);
		break;
	case FLAGES::CSHARP:
		objectsTree = new CSharpWriter(elemnts, w, h);
		break;
	default:
		std::cout << flag << " flage not found!." << std::endl;
		return 1;
	}

	objectsTree->createObjectsTree();
	objectsTree->writeObjectsTree(outputPath);
	delete objectsTree;
	return 0;
}