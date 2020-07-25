#ifndef CSHARP_OBJECTS_WRITER_
#define CSHARP_OBJECTS_WRITER_

#include <vector>

#include "equalizer/ObjectsTreeController.hpp"
#include "equalizer/Element.hpp"
#include "equalizer/CSharpObject.hpp"

class CSharpWriter: public ObjectsTreeController{
private:
    std::vector<CSharpObject> csharpObject;
    int width, height;
public:
    CSharpWriter(std::vector<Element> elements, int width, int height);
    void createObjectsTree();
    ~CSharpWriter();
};



#endif