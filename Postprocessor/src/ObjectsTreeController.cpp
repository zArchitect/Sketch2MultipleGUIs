
#include <vector>
#include <boost/property_tree/ptree.hpp>
#include <boost/property_tree/json_parser.hpp>

#include "equalizer/ObjectsTreeController.hpp"
#include "equalizer/Element.hpp"
boost::property_tree::ptree ObjectsTreeController::getObjectsTree(){
    return this->objectsTree;
}

ObjectsTreeController::ObjectsTreeController(std::vector<Element> elements){
    this->elements = elements;
}


void ObjectsTreeController::writeObjectsTree(std::string path){
    boost::property_tree::write_json(path, this->objectsTree);
}
