
#include <string>

#include "equalizer/CSharpObject.hpp"


const std::string CSharpObject::OBJECT_KEY = "object";
const std::string CSharpObject::ID_KEY = "id";
const std::string CSharpObject::LEFT_KEY = "left";
const std::string CSharpObject::TOP_KEY = "top";
const std::string CSharpObject::WIDTH_KEY = "width";
const std::string CSharpObject::HEIGHT_KEY = "height";


CSharpObject::CSharpObject(){}
CSharpObject::CSharpObject(std::string object, std::string id, double left, double top, double width, double height){
    this->object = object;
    this->id = id;
    this->left = left;
    this->top = top;
    this->width = width;
    this->height = height;
}

void CSharpObject::setObject(std::string object){
    this->object = object;
}
void CSharpObject::setId(std::string id){
    this->id = id;
}
void CSharpObject::setLeft(double left){
    this->left = left;
}
void CSharpObject::setTop(double top){
    this->top = top;
}
void CSharpObject::setWidth(double width){
    this->width = width;
}
void CSharpObject::setHeight(double height){
    this->height = height;
}

std::string CSharpObject::getObject()const{
    return this->object;
}
std::string CSharpObject::getId()const{
    return this->id;
}
double CSharpObject::getLeft()const{
    return this->left;
}
double CSharpObject::getTop()const{
    return this->top;
}
double CSharpObject::getWidth()const{
    return this->width;
}
double CSharpObject::getHeight()const{
    return this->height;
}