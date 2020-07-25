
#include<string>
#include<vector>

#include <equalizer/Element.hpp>

Element::Element(){
    this->leftRatio = 0;
    this->topRatio = 0;
    this->rightRatio = 0;
    this->bottomRatio = 0;
}

Element::Element(std::string view, std::string id,int left, int top, int right, int bottom){
    this->view = view;
    this->id = id;
    this->leftRatio = left;
    this->topRatio = top;
    this->rightRatio = right;
    this->bottomRatio = bottom;
}

void Element::setLeft(int left){
    this->leftRatio = left;
}

void Element::setView(std::string view){
    this->view = view;
}

void Element::setId(std::string id){
    this->id = id;
}

void Element::setTop(int top){
    this->topRatio = top;
}

void Element::setRight(int rightRatio){
    this->rightRatio = rightRatio;
}

void Element::setBottom(int bottomRatio){
    this->bottomRatio = bottomRatio;
}

int Element::getLeft(){
    return this->leftRatio;
}

int Element::getTop(){
    return this->topRatio;
}

int Element::getRight(){
    return this->rightRatio;
}

int Element::getBottom(){
    return this->bottomRatio;
}

std::string Element::getView(){
    return this->view;
}

std::string Element::getId(){
    return this->id;
}

std::string Element::toString(){
    return this->view + "\nleft: " + std::to_string(this->leftRatio) + "\ntop: "
            + std::to_string(this->topRatio) + "\nright: " + std::to_string(this->rightRatio) 
            + "\nbottom: " + std::to_string(this->bottomRatio);
}
