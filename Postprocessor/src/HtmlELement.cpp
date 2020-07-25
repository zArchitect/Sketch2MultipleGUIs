
#include <string>

#include "equalizer/HtmlElement.hpp"

const std::string HtmlElement::ELEMENT_KEY = "element";
const std::string HtmlElement::ID_KEY = "id";
const std::string HtmlElement::START_COLUMN_KEY = "start_column";
const std::string HtmlElement::TOP_KEY = "top";
const std::string HtmlElement::WIDTH_KEY = "width";
const std::string HtmlElement::HEIGHT_KEY = "height";


HtmlElement::HtmlElement(){}
HtmlElement::HtmlElement(std::string elementType, std::string id, int startColumn, int top, int width, int height){
    this->element = elementType;
    this->id = id;
    this->start_column = startColumn;
    this->top = top;
    this->width = width;
    this->height = height;
}
HtmlElement::~HtmlElement(){}
    

std::string HtmlElement::getElementType()const{return this->element;}
std::string HtmlElement::getId()const{return this->id;}
int HtmlElement::getStartColumn()const{return this->start_column;}
int HtmlElement::getTop()const{return this->top;}
int HtmlElement::getWidth()const{return this->width;}
int HtmlElement::getHeight()const{return this->height;}

void HtmlElement::setElementType(std::string elementype){this->element = elementype;}
void HtmlElement::setId(std::string id){this->id = id;}
void HtmlElement::setStartColumn(int s){this->start_column = s;}
void HtmlElement::setTop(int s){this->top = s;}
void HtmlElement::setWidth(int s){this->width = s;}
void HtmlElement::setHeight(int s){this->height = s;}