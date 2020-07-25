#ifndef HTML_ELEMENT_
#define HTML_ELEMENT_

#include<string>

class HtmlElement{

private:
    std::string element;
    std::string id;
    int start_column;
    int top;
    int width;
    int height;

public:
    const static std::string ELEMENT_KEY;
    const static std::string ID_KEY;
    const static std::string START_COLUMN_KEY;
    const static std::string TOP_KEY;
    const static std::string WIDTH_KEY;
    const static std::string HEIGHT_KEY;

    HtmlElement();
    HtmlElement(std::string, std::string, int, int, int, int);
    ~HtmlElement();
    
    void setElementType(std::string element);
    void setId(std::string id);
    void setStartColumn(int s);
    void setTop(int s);
    void setWidth(int s);
    void setHeight(int s);

    std::string getElementType()const;
    std::string getId()const;
    int getStartColumn()const;
    int getTop()const;
    int getWidth()const;
    int getHeight()const;
};


#endif