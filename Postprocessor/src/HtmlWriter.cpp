
#include <vector>
#include <boost/property_tree/ptree.hpp>
#include <iostream>

#include "equalizer/HtmlWriter.hpp"
#include "equalizer/Element.hpp"
#include "equalizer/Utilities.hpp"

int round(double val, double maxVal);
bool compareTop(HtmlElement &e1, HtmlElement &e2);
bool compareLeft(HtmlElement &e1, HtmlElement &e2);

HtmlWriter::HtmlWriter(std::vector<Element> elements, int maxWidth, int maxHeight)
:ObjectsTreeController(elements){
    this->maxHeight = maxHeight;
    this->maxWidth = maxWidth;
    for(size_t i =0; i < ObjectsTreeController::elements.size();++i){
        ObjectsTreeController::elements[i].setLeft(getPercent(elements[i].getLeft(), maxWidth));
        ObjectsTreeController::elements[i].setTop(getPercent(elements[i].getTop(), maxHeight));
        ObjectsTreeController::elements[i].setRight(getPercent(elements[i].getRight(), maxWidth));
        ObjectsTreeController::elements[i].setBottom(getPercent(elements[i].getBottom(), maxHeight));
    }
}

HtmlWriter::~HtmlWriter(){}

void HtmlWriter::createObjectsTree(){
	// std::cout << "elments size: " << ObjectsTreeController::elements.size() << std::endl;
    
    for(size_t i =0; i < ObjectsTreeController::elements.size();++i){
        HtmlElement e(ObjectsTreeController::elements[i].getView(), 
        ObjectsTreeController::elements[i].getId(), 
		ObjectsTreeController::elements[i].getLeft(), 
        ObjectsTreeController::elements[i].getTop(), 
        ObjectsTreeController::elements[i].getRight() - ObjectsTreeController::elements[i].getLeft(),
		ObjectsTreeController::elements[i].getBottom() - ObjectsTreeController::elements[i].getTop());
		this->htmlElements.push_back(e);
	}

    this->createElementsMatrix();
    boost::property_tree::ptree rows;
	for(size_t i =0; i<this->elmentsMatrix.size();++i){
        boost::property_tree::ptree row;
        for(size_t j =0; j < this->elmentsMatrix[i].size();++j){
            boost::property_tree::ptree child;
            child.add<std::string>(HtmlElement::ELEMENT_KEY, this->elmentsMatrix[i][j].getElementType());
            child.add<std::string>(HtmlElement::ID_KEY, this->elmentsMatrix[i][j].getId());
            child.add<int>(HtmlElement::START_COLUMN_KEY, this->elmentsMatrix[i][j].getStartColumn());
            child.add<int>(HtmlElement::WIDTH_KEY, this->elmentsMatrix[i][j].getWidth());
            child.add<int>(HtmlElement::HEIGHT_KEY, this->elmentsMatrix[i][j].getHeight());
            row.push_back(std::make_pair("", child));
        }
        boost::property_tree::ptree rowObject;
        rowObject.add_child("elements", row);
        rows.push_back(std::make_pair("", rowObject));
	}

    ObjectsTreeController::objectsTree.add_child("rows", rows);
	
	
}


void HtmlWriter::createElementsMatrix(){
    for(size_t i =0; i<=this->htmlElements.size();++i){
		std::sort(this->htmlElements.begin(), this->htmlElements.end(), compareTop);
		std::vector<HtmlElement> row;
		int initTopVal = this->htmlElements[0].getTop();
		size_t j;
        
		for(j =0; ( this->htmlElements[j].getTop() - initTopVal <= 10) && j < this->htmlElements.size();++j){
			row.push_back(this->htmlElements[j]);
            std::cout << htmlElements[j].getId() << std::endl;
		}
        std::sort(row.begin(), row.end(), compareLeft);
        this->elmentsMatrix.push_back(row);
		this->htmlElements.erase(this->htmlElements.begin(), this->htmlElements.begin()+j);
		i = 0;
	}

}

int round(double val, double maxVal){
	return (val/maxVal) * 100;
}

bool compareTop(HtmlElement &e1, HtmlElement &e2){
    return e1.getTop() < e2.getTop();
}


bool compareLeft(HtmlElement &e1, HtmlElement &e2){
    return e1.getStartColumn() < e2.getStartColumn();
}