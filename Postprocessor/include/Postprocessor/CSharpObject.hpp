#ifndef CSHARP_OBJECT_
#define CSHARP_OBJECT_

#include<string>

class CSharpObject{

private:
    std::string object;
    std::string id;
    double left;
    double top;
    double width;
    double height;

public:
    const static std::string OBJECT_KEY;
    const static std::string ID_KEY;
    const static std::string LEFT_KEY;
    const static std::string TOP_KEY;
    const static std::string WIDTH_KEY;
    const static std::string HEIGHT_KEY;

    CSharpObject();
    CSharpObject(std::string, std::string, double, double, double, double);

    std::string getObject()const;
    std::string getId()const;
    double getLeft()const;
    double getTop()const;
    double getWidth()const;
    double getHeight()const;

    void setObject(std::string object);
    void setId(std::string id);
    void setLeft(double left);
    void setTop(double top);
    void setWidth(double width);
    void setHeight(double height);
};

#endif