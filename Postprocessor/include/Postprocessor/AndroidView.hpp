#ifndef ANDROID_VIEW_
#define ANDROID_VIEW_

#include<string>

class AndroidView{

private:
    std::string view;
    std::string id;
    double start_percent;
    double top_percent;
    double end_percent;
    double bottom_percent;

public:
    const static std::string VIEW_KEY;
    const static std::string ID_KEY;
    const static std::string Start_PERCENT_KEY;
    const static std::string TOP_PERCENT_KEY;
    const static std::string END_PERCENT_KEY;
    const static std::string BOTTOM_PERCENT_KEY;

    AndroidView();
    AndroidView(std::string, std::string, double, double, double, double);

    void setView(std::string view);
    void setId(std::string id);
    void setStartPercent(double percent);
    void setTopPercent(double percent);
    void setEndPercent(double percent);
    void setBottomPercent(double percent);

    std::string getView()const;
    std::string getId()const;
    double getStartPercent()const;
    double getTopPercent()const;
    double getEndPercent()const;
    double getBottomPercent()const;
};

#endif