#ifndef HTML_WRITER_
#define HTML_WRITER_

#include <vector>

#include "equalizer/ObjectsTreeController.hpp"
#include "equalizer/Element.hpp"
#include "equalizer/HtmlElement.hpp"

class HtmlWriter: public ObjectsTreeController{
private:
    std::vector<HtmlElement> htmlElements;
    std::vector<std::vector<HtmlElement>> elmentsMatrix;
    int maxWidth, maxHeight;
    void createElementsMatrix();
public:
    HtmlWriter(std::vector<Element> elements, int maxWidth, int maxHeight);
    ~HtmlWriter();

    void createObjectsTree();
};


#endif