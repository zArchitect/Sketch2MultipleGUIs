#ifndef OBJECTS_TREE_CONTROLLER_
#define OBJECTS_TREE_CONTROLLER_

#include <string>
#include <boost/property_tree/ptree.hpp>
#include "equalizer/Element.hpp"

class ObjectsTreeController{
private:
    

protected:
  ObjectsTreeController(std::vector<Element> elements);
  boost::property_tree::ptree objectsTree;
  std::vector<Element> elements;
public:
    virtual void createObjectsTree() = 0;
    void writeObjectsTree(std::string path);
    boost::property_tree::ptree getObjectsTree();
    virtual ~ObjectsTreeController(){}
};

#endif